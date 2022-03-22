package com.studying.jokenpo.fragments

import android.text.style.TtsSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.studying.jokenpo.R


abstract class JokenpoBaseGameFragment : Fragment() {

    private val appSelectedOption: JokenpoOption = JokenpoOption.values().random()
    private val options = arrayOf(
        JokenpoOption.ROCK_OPTION,
        JokenpoOption.PAPER_OPTION,
        JokenpoOption.SCISSOR_OPTION
    )
    private val args: JokenpoResultGameFragmentArgs by navArgs()

    protected fun setClickButtonGameListener(
        gameOption: AppCompatImageView,
        playerSelectedOption: JokenpoOption,
    ) {
        gameOption.setOnClickListener {
            setResultScreen(playerSelectedOption)
        }
    }

    protected fun setClickStartGameListener(startGameButton: AppCompatButton) {
        startGameButton.setOnClickListener {
            startGame()
        }
    }

    private fun setResultScreen(playerSelectedOption: JokenpoOption) {
        val actionId = JokenpoStartGameFragmentDirections
            .actionJokenpoStartGameFragmentToJokenpoResultGameFragment(playerSelectedOption)
        findNavController().navigate(actionId)
    }

    protected fun showResultGame(
        textView: TextView,
    ) {
        when {
            setResultCombinations(Combination.DRAW_COMBINATION) -> {
                setResultText(Result.DRAW_GAME, textView)
            }
            setResultCombinations(Combination.LOSE_COMBINATION) -> {
                setResultText(Result.LOSE_GAME, textView)
            }
            setResultCombinations(Combination.WIN_COMBINATION) -> {
                setResultText(Result.WIN_GAME, textView)
            }
        }
    }

    private fun setResultCombinations(combination: Combination): Boolean {
        val playerSelectedOption = args.playerSelectedOption
        return when (combination) {
            Combination.DRAW_COMBINATION ->
                appSelectedOption == JokenpoOption.ROCK_OPTION && playerSelectedOption == JokenpoOption.ROCK_OPTION
                        || appSelectedOption == JokenpoOption.PAPER_OPTION && playerSelectedOption == JokenpoOption.PAPER_OPTION
                        || appSelectedOption == JokenpoOption.SCISSOR_OPTION && playerSelectedOption == JokenpoOption.SCISSOR_OPTION

            Combination.LOSE_COMBINATION ->
                appSelectedOption == JokenpoOption.ROCK_OPTION && playerSelectedOption == JokenpoOption.SCISSOR_OPTION
                        || appSelectedOption == JokenpoOption.PAPER_OPTION && playerSelectedOption == JokenpoOption.ROCK_OPTION
                        || appSelectedOption == JokenpoOption.SCISSOR_OPTION && playerSelectedOption == JokenpoOption.PAPER_OPTION

            Combination.WIN_COMBINATION ->
                appSelectedOption == JokenpoOption.ROCK_OPTION && playerSelectedOption == JokenpoOption.PAPER_OPTION
                        || appSelectedOption == JokenpoOption.PAPER_OPTION && playerSelectedOption == JokenpoOption.SCISSOR_OPTION
                        || appSelectedOption == JokenpoOption.SCISSOR_OPTION && playerSelectedOption == JokenpoOption.ROCK_OPTION
        }
    }

    private fun setResultText(result: Result, textView: TextView) {
        when (result) {
            Result.DRAW_GAME -> textView.text = getString(R.string.draw_game)
            Result.WIN_GAME -> textView.text = getString(R.string.win_game)
            Result.LOSE_GAME -> textView.text = getString(R.string.lose_game)
        }
    }

    protected fun showPlayerSelectedOption(resultPlayerImageView: ImageView) {
        val playerSelectedOption = args.playerSelectedOption
        when (playerSelectedOption) {
            JokenpoOption.ROCK_OPTION -> {
                changeTheImage(resultPlayerImageView, R.drawable.rock_hand_up)
            }
            JokenpoOption.PAPER_OPTION -> {
                changeTheImage(resultPlayerImageView, R.drawable.paper_hand_up)
            }
            JokenpoOption.SCISSOR_OPTION -> {
                changeTheImage(resultPlayerImageView, R.drawable.scissor_hand_up)
            }
        }
    }

    protected fun showAppSelectedOption(resultAppImageView: ImageView) {
        when (options[appSelectedOption.ordinal]) {
            JokenpoOption.ROCK_OPTION -> {
                changeTheImage(resultAppImageView, R.drawable.rock_hand_down)
            }
            JokenpoOption.PAPER_OPTION -> {
                changeTheImage(resultAppImageView, R.drawable.paper_hand_down)
            }
            JokenpoOption.SCISSOR_OPTION -> {
                changeTheImage(resultAppImageView, R.drawable.scissor_hand_down)
            }
        }
    }

    private fun changeTheImage(anyImage: ImageView, imageButton: Int) {
        anyImage.setImageResource(imageButton)
    }

    protected fun startGame() {
        findNavController().navigate(R.id.jokenpoStartGameFragment)
    }

}

enum class Result {
    DRAW_GAME,
    WIN_GAME,
    LOSE_GAME
}

enum class Combination {
    DRAW_COMBINATION,
    WIN_COMBINATION,
    LOSE_COMBINATION
}

enum class JokenpoOption {
    ROCK_OPTION,
    PAPER_OPTION,
    SCISSOR_OPTION
}
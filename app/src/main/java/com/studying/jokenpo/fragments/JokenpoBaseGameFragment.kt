package com.studying.jokenpo.fragments

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.studying.jokenpo.R


abstract class JokenpoBaseGameFragment : Fragment() {

    private val appSelectedOption = java.util.Random().nextInt(3)
    private val options = arrayOf("Rock", "Paper", "Scissor")
    private val args: JokenpoResultGameFragmentArgs by navArgs()

    protected fun setClickButtonGameListener(
        gameOption: AppCompatImageView,
        playerSelectedOption: String,
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

    private fun setResultScreen(playerSelectedOption: String) {
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
                appSelectedOption == 0 && playerSelectedOption == "Rock"
                        || appSelectedOption == 1 && playerSelectedOption == "Paper"
                        || appSelectedOption == 2 && playerSelectedOption == "Scissor"

            Combination.LOSE_COMBINATION ->
                appSelectedOption == 0 && playerSelectedOption == "Scissor"
                        || appSelectedOption == 1 && playerSelectedOption == "Rock"
                        || appSelectedOption == 2 && playerSelectedOption == "Paper"

            Combination.WIN_COMBINATION ->
                appSelectedOption == 0 && playerSelectedOption == "Paper"
                        || appSelectedOption == 1 && playerSelectedOption == "Scissor"
                        || appSelectedOption == 2 && playerSelectedOption == "Rock"
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
            "Rock" -> {
                changeTheImage(resultPlayerImageView, R.drawable.rock_hand_up)
            }
            "Paper" -> {
                changeTheImage(resultPlayerImageView, R.drawable.paper_hand_up)
            }
            "Scissor" -> {
                changeTheImage(resultPlayerImageView, R.drawable.scissor_hand_up)
            }
        }
    }

    protected fun showAppSelectedOption(anyImage: ImageView) {
        when (options[appSelectedOption]) {
            "Rock" -> {
                changeTheImage(anyImage, R.drawable.rock_hand_down)
            }
            "Paper" -> {
                changeTheImage(anyImage, R.drawable.paper_hand_down)
            }
            "Scissor" -> {
                changeTheImage(anyImage, R.drawable.scissor_hand_down)
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
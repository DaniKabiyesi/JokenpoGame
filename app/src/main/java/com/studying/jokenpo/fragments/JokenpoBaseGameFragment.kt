import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.studying.jokenpo.R
import com.studying.jokenpo.fragments.JokenpoResultGameFragmentArgs
import com.studying.jokenpo.fragments.JokenpoStartGameFragmentDirections


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

    protected fun setClickRestartGameListener(restartGameButton: AppCompatButton) {
        restartGameButton.setOnClickListener {
            restartGame()
        }
    }

    private fun setResultScreen(playerSelectedOption: String) {
        val actionId = JokenpoStartGameFragmentDirections
            .actionJokenpoStartGameFragmentToJokenpoResultGameFragment(playerSelectedOption)
        findNavController().navigate(actionId)
    }

    protected fun showResultGame(
        textView: TextView
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

    protected fun setResultCombinations(combination: Combination): Boolean {
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

//    private fun teste(textView: TextView) {
//        val playerSelectedOption = args.playerSelectedOption
//        when {
//            setResultCombinations(Combination.WIN_COMBINATION) -> {
//                setResultText(Result.LOSE_GAME, textView)
//            }
//        }
//    }

    protected fun setResultText(result: Result, textView: TextView) {
        when (result) {
            Result.DRAW_GAME -> textView.text = "IT'S A DRAW"

            Result.WIN_GAME -> textView.text = "YOU WIN"

            Result.LOSE_GAME -> textView.text = "YOU LOSE"
        }
    }

    protected fun showPlayerSelectedOption(resultPlayerImageView: ImageView) {
        val playerSelectedOption = args.playerSelectedOption
        when (playerSelectedOption) {
            "Rock" -> {
                changeTheImage(resultPlayerImageView, R.drawable.rock_hand_down)
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
                changeTheImage(anyImage, R.drawable.rock_hand_up)
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

    private fun restartGame() {
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
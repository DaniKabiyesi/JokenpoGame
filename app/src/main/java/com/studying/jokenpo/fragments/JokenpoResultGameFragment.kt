package com.studying.jokenpo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.studying.jokenpo.R
import com.studying.jokenpo.databinding.FragmentJokenpoResultGameBinding


class JokenpoResultGameFragment : JokenpoBaseGameFragment() {

    private lateinit var _binding: FragmentJokenpoResultGameBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentJokenpoResultGameBinding.inflate(
            inflater,
            container,
            false
        )
        setListeners()
        showAppSelectedOption(binding.resultAppHandImageView)
        showPlayerSelectedOption(binding.resultPlayerHandImageView)
        showResultGame(binding.resultGameTextView)
        return binding.root
    }

    private fun setListeners() {
        binding.run {
            reStartGameBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}

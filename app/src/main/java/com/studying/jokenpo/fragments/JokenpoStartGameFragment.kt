package com.studying.jokenpo.fragments

import JokenpoBaseGameFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studying.jokenpo.databinding.FragmentJokenpoStartGameBinding

class JokenpoStartGameFragment : JokenpoBaseGameFragment() {

    private lateinit var _binding: FragmentJokenpoStartGameBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentJokenpoStartGameBinding.inflate(
            inflater,
            container,
            false
        )
        setListeners()



        return binding.root
    }

    private fun setListeners() {
        binding.run {
            setClickButtonGameListener(rockImageView, "Rock")
            setClickButtonGameListener(paperImageView, "Paper")
            setClickButtonGameListener(scissorImageView, "Scissor")
        }
    }
}
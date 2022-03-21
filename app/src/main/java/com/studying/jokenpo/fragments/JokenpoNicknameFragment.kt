package com.studying.jokenpo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.studying.jokenpo.databinding.FragmentJokenpoNicknameGameBinding

class JokenpoNicknameFragment : JokenpoBaseGameFragment() {

    private lateinit var _binding: FragmentJokenpoNicknameGameBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentJokenpoNicknameGameBinding.inflate(
            inflater,
            container,
            false
        )
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        setClickNicknameListener()
    }

    private fun setClickNicknameListener() {
        binding.run {
            startGameBtn.setOnClickListener {
                val message = if (nicknameIsNotBlank()) {
                    startGame()
                    "Have a nice game!"
                } else {
                    "Enter your nickname, please!"
                }
                Toast.makeText(
                    requireContext(),
                    message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun nicknameIsNotBlank(): Boolean {
        return binding.nicknameEditText.text.toString().isNotBlank()
    }

}

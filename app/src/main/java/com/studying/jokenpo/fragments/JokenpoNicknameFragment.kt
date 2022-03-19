package com.studying.jokenpo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.studying.jokenpo.R
import com.studying.jokenpo.databinding.FragmentJokenpoNicknameGameBinding

class JokenpoNicknameFragment : Fragment() {

    private lateinit var _binding: FragmentJokenpoNicknameGameBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJokenpoNicknameGameBinding.inflate(
            inflater,
            container,
            false
        )
        setListeners()
        return binding.root
    }

    private fun setListeners(){
        binding.run{
            startGameBtn.setOnClickListener {
                findNavController().navigate(R.id.jokenpoStartGameFragment)
            }
        }
    }

}
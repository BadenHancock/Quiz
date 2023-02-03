package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.quiz.databinding.FragmentCheatBinding
import com.example.quiz.databinding.FragmentGameWonBinding


class CheatFragment : Fragment() {
    private var _binding: FragmentCheatBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheatBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val args = CheatFragmentArgs.fromBundle((requireArguments()))
        val answer = args.answerArg
        var cheat = false
        binding.answeText.text = answer.toString()
        binding.button.setOnClickListener() {
            binding.answeText.visibility = VISIBLE
            cheat = true
            setFragmentResult("REQUESTING_REPLY_KEY", bundleOf("REPLY_KEY" to true))
        }
        cheat = false
        setFragmentResult("REQUESTING_REPLY_KEY", bundleOf("REPLY_KEY" to false))
        return rootView
    }
}
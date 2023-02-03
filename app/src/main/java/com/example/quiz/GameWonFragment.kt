package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quiz.databinding.FragmentGameWonBinding
import kotlin.math.ceil


class GameWonFragment : Fragment() {
    private var _binding: FragmentGameWonBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameWonBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val wrong = args.incorrectArg
        binding.textView2.text = "You had " + wrong + " wrong answers"
        return rootView
    }
}
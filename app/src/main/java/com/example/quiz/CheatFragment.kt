package com.example.quiz

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.View.VISIBLE
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quiz.databinding.FragmentCheatBinding
import com.example.quiz.databinding.FragmentGameWonBinding


class CheatFragment : Fragment() {
    private var _binding: FragmentCheatBinding? = null
    private val binding get() = _binding!!
    private  val viewModel:QuizViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheatBinding.inflate(inflater, container, false)
        val rootView = binding.root
        setHasOptionsMenu(true)
        val answer = viewModel.currentQuestionAnswer.toString()
        var cheat = false
        binding.answerText.text = answer.toString()
        binding.showAnswerButton.setOnClickListener() {
            binding.answerText.visibility = VISIBLE
            viewModel.setCheatedStatusForCurrentQuestion(true)
        }
        viewModel.setCheatedStatusForCurrentQuestion(false)
        return rootView
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
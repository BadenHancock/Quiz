package com.example.quiz

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.View.VISIBLE
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
        setHasOptionsMenu(true)
        val args = CheatFragmentArgs.fromBundle((requireArguments()))
        val answer = args.answerArg
        var cheat = false
        binding.answerText.text = answer.toString()
        binding.showAnswerButton.setOnClickListener() {
            binding.answerText.visibility = VISIBLE
            cheat = true
            setFragmentResult("REQUESTING_REPLY_KEY", bundleOf("REPLY_KEY" to true))
        }
        cheat = false
        setFragmentResult("REQUESTING_REPLY_KEY", bundleOf("REPLY_KEY" to false))
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
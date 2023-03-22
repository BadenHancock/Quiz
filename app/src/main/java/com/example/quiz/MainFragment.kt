package com.example.quiz

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quiz.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.activityViewModels

//
const val KEY_QUESTION_NUM = "questionNum"
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    private  val viewModel:QuizViewModel by activityViewModels()
//    lateinit var mediaPlayer : MediaPlayer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root

        setHasOptionsMenu(true)

        viewModel.listIndex.observe(viewLifecycleOwner) {
            binding.question.text = getString(viewModel.currentQuestionText)
        }

        viewModel.gameWon.observe(viewLifecycleOwner) {
            if(viewModel.gameWon.value?:0 == true) {
                val action = MainFragmentDirections.actionMainFragment2ToGameWonFragment()
                rootView.findNavController().navigate(action)
            }
        }

        binding.trueButton.setOnClickListener() {
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener() {
            checkAnswer(false)
        }
        binding.cheatButton.setOnClickListener() {
            val action = MainFragmentDirections.actionMainFragment2ToCheatFragment2()
            rootView.findNavController().navigate(action)
        }
        binding.nextButton.setOnClickListener() {
            viewModel.setNextQuestion()
        }
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
    private fun checkAnswer(userAnswer : Boolean) {
        if(viewModel.checkAnswer(userAnswer)) {
            if(!viewModel.currentQuestionCheatStatus) {
                Toast.makeText(activity, R.string.True, Toast.LENGTH_SHORT).show()
//                mediaPlayer = MediaPlayer.create(context, R.raw.yes)
//                mediaPlayer.start()
            }
            else {
                Snackbar.make(binding.root, "Cheating isn't right", Snackbar.LENGTH_SHORT).show()
            }
        }
        else  {
            Toast.makeText(activity, R.string.False, Toast.LENGTH_SHORT).show()
//            mediaPlayer = MediaPlayer.create(context, R.raw.no)
//            mediaPlayer.start()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    override fun onSaveInstanceState(savedInstanceState: Bundle) {
//        super.onSaveInstanceState(savedInstanceState)
//        savedInstanceState.putInt(KEY_QUESTION_NUM, arr)
//    }
}

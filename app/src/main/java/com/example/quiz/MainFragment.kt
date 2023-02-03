package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.quiz.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

//
const val KEY_QUESTION_NUM = "questionNum"
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    data class Question(val question : Int,val isTrue : Boolean)
    lateinit var question : List<Question>
    var wrong = 0
    var correct = 0
    var arr = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        if(savedInstanceState != null) {
            arr = savedInstanceState.getInt(KEY_QUESTION_NUM)
        }
        var cheat = false
        setFragmentResultListener("REQUESTING_REPLY_KEY") { requestKey: String, bundle: Bundle ->
            cheat = bundle.getBoolean("REPLY_KEY")
        }

        val questions = listOf<Question>(
            Question(R.string.question_one, false),
            Question(R.string.question_two, false),
            Question(R.string.question_three, true)
            ,
            Question(R.string.question_four, true),
            Question(R.string.question_five, true)
        )

        binding.question.text = getString(questions.get(arr).question)
        fun checkAnswer(userAnswer : Boolean) {
            if(userAnswer == questions.get(arr).isTrue) {
                Toast.makeText(activity, R.string.True, Toast.LENGTH_SHORT).show()
                correct++
                if(correct == 3 ) {
                    val action = MainFragmentDirections.actionMainFragment2ToGameWonFragment(wrong)
                    rootView.findNavController().navigate(action)
                }
                if(cheat) {
                    Snackbar.make(binding.root, "Cheating isnt right", Snackbar.LENGTH_SHORT).show()
                    cheat=false
                }
            }
            else  {
                Toast.makeText(activity, R.string.False, Toast.LENGTH_SHORT).show()
                wrong++
            }
        }
        binding.trueButton.setOnClickListener() {view ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener() {view ->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener() {view ->
            if(arr == questions.size-1) {
                    arr=0
                }
            else
                arr++
            binding.question.text = getString(questions.get(arr).question)
        }
        binding.cheatButton.setOnClickListener() {
            val action = MainFragmentDirections.actionMainFragment2ToCheatFragment2(questions.get(arr).isTrue)
            rootView.findNavController().navigate(action)
        }
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(KEY_QUESTION_NUM, arr)
    }
}
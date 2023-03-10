package com.example.quiz

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quiz.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

//
const val KEY_QUESTION_NUM = "questionNum"
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    lateinit var question : List<Question>
    var wrong = 0
    var correct = 0
    var arr = 0
    lateinit var mediaPlayer : MediaPlayer
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        if(savedInstanceState != null) {
            arr = savedInstanceState.getInt(KEY_QUESTION_NUM)
        }
        setHasOptionsMenu(true)
        var cheat = false
        setFragmentResultListener("REQUESTING_REPLY_KEY") { requestKey: String, bundle: Bundle ->
            cheat = bundle.getBoolean("REPLY_KEY")
        }

        val questions = listOf<Question>(
            Question(R.string.question_one, false),
            Question(R.string.question_two, false),
            Question(R.string.question_three, true),
            Question(R.string.question_four, true),
            Question(R.string.question_five, true)
        )

        binding.question.text = getString(questions.get(arr).question)
        fun checkAnswer(userAnswer : Boolean) {
            if(userAnswer == questions.get(arr).isTrue) {
                Toast.makeText(activity, R.string.True, Toast.LENGTH_SHORT).show()
                mediaPlayer = MediaPlayer.create(context, R.raw.yes)
                mediaPlayer.start()
                if(!cheat) {
                    correct++
                }
                if(correct == 3 ) {
                    val action = MainFragmentDirections.actionMainFragment2ToGameWonFragment(wrong)
                    rootView.findNavController().navigate(action)
                }

                if(cheat) {
                    Snackbar.make(binding.root, "Cheating isn't right", Snackbar.LENGTH_SHORT).show()
                    cheat=false
                }

            }
            else  {
                Toast.makeText(activity, R.string.False, Toast.LENGTH_SHORT).show()
                mediaPlayer = MediaPlayer.create(context, R.raw.no)
                mediaPlayer.start()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
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

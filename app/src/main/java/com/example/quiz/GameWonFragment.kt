package com.example.quiz

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quiz.databinding.FragmentGameWonBinding
import kotlin.math.ceil


class GameWonFragment : Fragment() {
    private var _binding: FragmentGameWonBinding? = null
    private val binding get() = _binding!!
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameWonBinding.inflate(inflater, container, false)
        val rootView = binding.root
        setHasOptionsMenu(true)
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        mediaPlayer = MediaPlayer.create(context,R.raw.epic)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        binding.imageButton.setImageResource(R.drawable.pause)
        val wrong = args.incorrectArg
        binding.textView2.text = "You had " + wrong + " wrong answers"
        binding.imageButton.setOnClickListener() {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.imageButton.setImageResource((R.drawable.play))
            }
            else{
                mediaPlayer.start()
                binding.imageButton.setImageResource(R.drawable.pause)
            }
        }
        binding.rewind.setOnClickListener() {
            val current  = mediaPlayer.currentPosition
            mediaPlayer.seekTo(current-10000)
        }
        binding.fastforward.setOnClickListener() {
            val current  = mediaPlayer.currentPosition
            mediaPlayer.seekTo(current+10000)
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

    override fun onStop() {
        super.onStop()
        mediaPlayer.release()
    }
}
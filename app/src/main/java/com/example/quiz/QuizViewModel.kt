package com.example.quiz

import android.media.MediaPlayer
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

class QuizViewModel : ViewModel()  {
    val questions  = listOf<Question>(
        Question(R.string.question_one, false),
        Question(R.string.question_two, false),
        Question(R.string.question_three, true),
        Question(R.string.question_four, true),
        Question(R.string.question_five, true))

    private var _incorrectAnswers = 0
    val incorrectAnswers : Int
        get() = _incorrectAnswers

    private var _correctAnswers = 0
    val correctAnswers : Int
        get() = _correctAnswers

    private val _listIndex = MutableLiveData(0)
    val listIndex : LiveData<Int>
        get() = _listIndex


    private val _gameWon = MutableLiveData(false)
    val gameWon : LiveData<Boolean>
        get() = _gameWon

    val currentQuestionAnswer: Boolean
        get() = questions.get(_listIndex.toString().toInt()).isTrue
    val currentQuestionText: String
        get() = questions.get(_listIndex.toString().toInt()).question.toString()
    val currentQuestionCheatStatus: Boolean
        get() = questions.get(_listIndex.toString().toInt()).cheated

    fun setCheatedStatusForCurrentQuestion(a: Boolean) {
        questions.get(_listIndex.toString().toInt()).cheated = a
    }

    fun checkIfGameWon() {
        if (_correctAnswers == 3) {
            _gameWon.value = true
        }
    }
    fun checkAnswer(userAnswer : Boolean) : Boolean{
        if(userAnswer == currentQuestionAnswer) {
//            Toast.makeText(activity, R.string.True, Toast.LENGTH_SHORT).show()
//            mediaPlayer = MediaPlayer.create(context, R.raw.yes)
//            mediaPlayer.start()
            if(!questions.get(_listIndex.value?:0).cheated) {
                _correctAnswers++
            }
            else {
//                Snackbar.make(binding.root, "Cheating isn't right", Snackbar.LENGTH_SHORT).show()
                questions.get(_listIndex.toString().toInt()).cheated=false
            }
            checkIfGameWon()

        }
        else  {
//            Toast.makeText(activity, R.string.False, Toast.LENGTH_SHORT).show()
//            mediaPlayer = MediaPlayer.create(context, R.raw.no)
//            mediaPlayer.start()
            _incorrectAnswers++
        }
        return userAnswer == currentQuestionAnswer
    }
    fun setNextQuestion() {
        if(_listIndex.value == questions.size-1) {
            _listIndex.value=0
        }
        else
            _listIndex.value = _listIndex.value?:0+1
    }
}
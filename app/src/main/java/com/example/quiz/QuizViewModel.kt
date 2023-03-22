package com.example.quiz

import android.media.MediaPlayer
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

class QuizViewModel : ViewModel() {
    val questions = listOf<Question>(
        Question(R.string.question_one, false),
        Question(R.string.question_two, false),
        Question(R.string.question_three, true),
        Question(R.string.question_four, true),
        Question(R.string.question_five, true)
    )

    private var _incorrectAnswers = 0
    val incorrectAnswers: Int
        get() = _incorrectAnswers

    private var _correctAnswers = 0
    val correctAnswers: Int
        get() = _correctAnswers

    private val _listIndex = MutableLiveData(0)
    val listIndex: LiveData<Int>
        get() = _listIndex

    private val _gameWon = MutableLiveData(false)
    val gameWon: LiveData<Boolean>
        get() = _gameWon

    val currentQuestionAnswer: Boolean
        get() = questions.get(_listIndex.value ?: 0).isTrue
    val currentQuestionText: Int
        get() = questions.get(_listIndex.value ?: 0).question
    val currentQuestionCheatStatus: Boolean
        get() = questions.get(_listIndex.value ?: 0).cheated

    fun setCheatedStatusForCurrentQuestion(a: Boolean) {
        questions.get(_listIndex.value ?: 0).cheated = a
    }

    fun checkIfGameWon(){
        _gameWon.value = _correctAnswers == 3
    }

    fun checkAnswer(userAnswer: Boolean): Boolean {
        if (userAnswer == currentQuestionAnswer) {
            if (!currentQuestionCheatStatus) {
                _correctAnswers++
                checkIfGameWon()
                return true
            }

        } else {
            _incorrectAnswers++
            return false
        }
        return true
    }

    fun setNextQuestion() {
        val currentIndex = _listIndex.value ?: 0
        if (_listIndex.value == questions.size - 1) {
            _listIndex.value = 0
        } else
            _listIndex.value = currentIndex + 1
    }
}
package com.example.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class QuizViewModel : Question()  {
    val questions  = listOf<Question>(
        Question(R.string.question_one, false, ),
        Question(R.string.question_two, false),
        Question(R.string.question_three, true),
        Question(R.string.question_four, true),
        Question(R.string.question_five, true))

    private val _incorrectAnswers = 0
    val incorrectAnswers : Int
        get() = _incorrectAnswers

    private val _correctAnswers = 0
    val correctAnswers : Int
        get() = _correctAnswers

    private val _listIndex = MutableLiveData(0)
    val listIndex : LiveData<Int>
        get() = _listIndex


    private val _gameWon = MutableLiveData(false)
    val gameWon : LiveData<Boolean>
        get() = _gameWon

    val currentQuestionAnswer : Boolean
        get() = questions.get(listIndex).isTrue
    val currentQuestionText : String
        get() = questions.get(listIndex).question.toString()
    val currentQuestionCheatStatus : Boolean
        get() = questions.get(listIndex).cheated

    fun setCheatedStatusForCurrentQuestion( a : Boolean){
        currentQuestionCheatStatus
    }

}
package com.example.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class QuizViewModel(val listofQuestion: Question, ) {

    private val _incorrectAnswers = MutableLiveData(0)
    val incorrectAnswers : LiveData<Int>
        get() = _incorrectAnswers

    private val _cheatedQuestions = MutableLiveData(0)
    val cheatedQuestions : LiveData<Int>
        get() = _cheatedQuestions

    private val _listIndex = MutableLiveData(0)
    val listIndex : LiveData<Int>
        get() = _listIndex

    var correctQuestions = 0


    private val _gameWon = MutableLiveData(false)
    val gameWon : LiveData<Boolean>
        get() = _gameWon

    val currentQuestionAnswer : Boolean
        get() =
    val currentQuestionText : String
        get() =
    val currentQuestionCheatStatus : Boolean
        get() =

}
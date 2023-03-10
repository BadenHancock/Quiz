package com.example.quiz

class Question {
    data class Question(val question : Int,val isTrue : Boolean, var cheated : Boolean = false)
}
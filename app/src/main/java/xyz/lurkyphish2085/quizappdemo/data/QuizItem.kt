package xyz.lurkyphish2085.quizappdemo.data

import java.lang.IllegalArgumentException

data class QuizItem(val question: String, val choices: Array<String>, val answer: String) {

    init {
        if (!choices.contains(answer)) {
            throw IllegalArgumentException("Supplied answer does not belong to supplied choices")
        }
    }
}
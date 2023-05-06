package xyz.lurkyphish2085.quizappdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.lurkyphish2085.quizappdemo.data.QuizDemo

class SharedViewModel : ViewModel() {

    private var _quiz = QuizDemo().instance
    private var _progressValue = MutableLiveData(_quiz.currentQuizCount * (100/_quiz.size))
    private var _quizCountText = MutableLiveData("Quiz ${_quiz.currentQuizCount} of ${_quiz.size}")
    private var _quizQuestion = MutableLiveData(_quiz.currentQuizItem.question)
    private var _quizChoices = MutableLiveData(_quiz.currentQuizItem.choices)
    private var _quizHasNext = MutableLiveData(_quiz.hasNext)
    private var _quizHasPrev = MutableLiveData(_quiz.hasPrev)

    val progressValue: LiveData<Int> = _progressValue
    val quizCountText: LiveData<String> = _quizCountText
    val quizQuestion: LiveData<String> = _quizQuestion
    val quizChoices: LiveData<Array<String>> = _quizChoices
    val quizHasNext: LiveData<Boolean> = _quizHasNext
    val quizHasPrev: LiveData<Boolean> = _quizHasPrev

    val score get() = "${_quiz.score}/${_quiz.size}"
    val scorePercentage get() = (_quiz.score / _quiz.size) * 100
    val resultFeedback get() =
        when (scorePercentage) {
            0 -> "BRUH!!! TRY AGAIN"
            in 70 .. 80 -> "YOU'RE DOING GOOD"
            in 90..95 -> "GREAT!!!"
            99 -> "THAT WAS CLOSE!!!"
            100 -> "PERFECT!!!!!"
            else -> "HAHAHAHAHAHAHA"
        }

    fun nextQuizItem() {
        _quiz.next()
        refreshData()
    }

    fun prevQuizItem() {
        _quiz.prev()
        refreshData()
    }

    fun answerQuiz(answer: String) {
        _quiz.inputAnswer(answer)
    }

    // I should have made my Quiz class properties as MutableLiveData
    private fun refreshData() {
        _progressValue.value = (_quiz.currentQuizCount * (100/_quiz.size)).coerceAtMost(100)
        _quizCountText.value = "Quiz ${_quiz.currentQuizCount} of ${_quiz.size}"
        _quizQuestion.value = _quiz.currentQuizItem.question
        _quizChoices.value = _quiz.currentQuizItem.choices

        _quizHasNext.value = _quiz.hasNext
        _quizHasPrev.value = _quiz.hasPrev
    }
}
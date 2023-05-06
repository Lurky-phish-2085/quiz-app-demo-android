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

    private var _progressMaxValue = _quiz.size

    val progressValue: LiveData<Int> = _progressValue
    val quizCountText: LiveData<String> = _quizCountText
    val quizQuestion: LiveData<String> = _quizQuestion
    val quizChoices: LiveData<Array<String>> = _quizChoices

    val progressMaxValue = _progressMaxValue

    fun nextQuizItem() {
        _quiz.next()
        refreshData()
    }

    fun prevQuizItem() {
        _quiz.prev()
        refreshData()
    }

    private fun refreshData() {
        // TODO(ADD MORE Data Bindings)
        _progressValue.value = (_quiz.currentQuizCount * (100/_quiz.size)).coerceAtMost(100)
        _quizCountText.value = "Quiz ${_quiz.currentQuizCount} of ${_quiz.size}"
        _quizQuestion.value = _quiz.currentQuizItem.question
        _quizChoices.value = _quiz.currentQuizItem.choices
    }
}
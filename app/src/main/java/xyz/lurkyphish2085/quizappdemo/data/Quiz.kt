package xyz.lurkyphish2085.quizappdemo.data

class Quiz(private vararg val _items: QuizItem) {

    private var _currentQuizIndex = 0
    private var _userAnswers = arrayOfNulls<String>(_items.size)

    val currentQuizItem get() = _items[_currentQuizIndex]
    val currentQuizCount get() = _currentQuizIndex + 1
    val size get() = _items.size
    val hasNext get() = _currentQuizIndex >= (_items.size - 1)
    val hasPrev get() = _currentQuizIndex <= 0

    val score get() = computeScore()

    fun next() {
        if (hasNext) return
        _currentQuizIndex++
    }

    fun prev() {
        if (hasPrev) return
        _currentQuizIndex--
    }

    fun inputAnswer(answer: String) {
        _userAnswers[_currentQuizIndex] = answer
    }

    private fun computeScore(): Int {
        var score = 0
        _items.forEachIndexed { index, item ->
            run {
                if (item.answer.equals(_userAnswers[index])) score++
            }
        }

        return score
    }
}
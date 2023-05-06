package xyz.lurkyphish2085.quizappdemo.data

class QuizDemo {

    val instance =
        Quiz(
            QuizItem(
                "What is the first name of Hitler?",
                arrayOf("Adolf", "Adolfo", "Nita"),
                "Adolf"
            ),
            QuizItem(
                "Who is Big Moustache Walrus man?",
                arrayOf("Joseph Stalin", "Adolf Hiter", "Hiroshito"),
                "Joseph Stalin"
            ),
            QuizItem(
                "Which of the following choices with the word RIGHT ANSWER?",
                arrayOf("RIGHT ANSWER", "WROPNG ANDSWER", "WRONG ANSWER"),
                "RIGHT ANSWER"
            )
        )
}
package com.example.drivinglicenseinfo

data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String ,
    val time: String,
    val questionList: List<QuestionModel>,
    val isHidden: Boolean = false
)
{
      constructor() : this("","","","", emptyList())
}
data class QuestionModel(
    val question : String,
    val options : List<String>,
    val correct : String,
    val isHidden: Boolean = false
)
{
    constructor() : this ("", emptyList(),"", false)
}
data class PracticeTestQuestionModel(
    val question: String,
    val options: List<String>,
    val correct: String,
    val tip: String // Tip for helping remember the correct answer
)

{
    constructor() : this ("", emptyList(),"","")
}
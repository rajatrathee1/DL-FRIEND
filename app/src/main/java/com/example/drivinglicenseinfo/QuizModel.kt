package com.example.drivinglicenseinfo
import android.os.Parcel
import android.os.Parcelable

data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val time: String,
    val questionList: List<QuestionModel>,
    val isHidden: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(QuestionModel)!!,  // Read the list of questions
        parcel.readByte() != 0.toByte() // Convert byte back to Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(time)
        parcel.writeTypedList(questionList)
        parcel.writeByte(if (isHidden) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuizModel> {
        override fun createFromParcel(parcel: Parcel): QuizModel {
            return QuizModel(parcel)
        }

        override fun newArray(size: Int): Array<QuizModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correct: String,
    val optionalImageResource: Int? = null, // Image resource
    var userAnswer: String? = null // New field to store user's answer
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeStringList(options)
        parcel.writeString(correct)
        parcel.writeValue(optionalImageResource)
        parcel.writeString(userAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionModel> {
        override fun createFromParcel(parcel: Parcel): QuestionModel {
            return QuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<QuestionModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class PracticeTestQuestionModel(
    val question: String,
    val options: List<String>,
    val correct: String,
    val tip: String // Tip for helping remember the correct answer
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeStringList(options)
        parcel.writeString(correct)
        parcel.writeString(tip)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PracticeTestQuestionModel> {
        override fun createFromParcel(parcel: Parcel): PracticeTestQuestionModel {
            return PracticeTestQuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<PracticeTestQuestionModel?> {
            return arrayOfNulls(size)
        }
    }
}

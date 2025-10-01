package ru.kirill.ecquizgame.customview.game

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class QuestionTextView : AppCompatTextView, UpdateText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun updateText(text: String) {
        this.text = text
    }

    override fun getFreezesText(): Boolean = true
}

interface UpdateText {
    fun updateText(text: String)
}
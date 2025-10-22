package ru.kirill.ecquizgame.customview.game

import android.content.Context
import android.graphics.Color
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import ru.kirill.ecquizgame.fragments.game.ChoiceUiState

class ChoiceButton : AppCompatButton, UpdateChoiceButton {

    private lateinit var state: ChoiceUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun update(state: ChoiceUiState) {
        Log.d("dd33", "update state inside choiceButton")
        this.state = state
        state.update(this)
    }

    override fun update(
        color: String,
        clickable: Boolean,
        isEnabled: Boolean
    ) {
        Log.d("dd33", "update params inside choiceButton")
        this.isEnabled = isEnabled
        setBackgroundColor(Color.parseColor(color))
        isClickable = clickable
    }

    override fun updateText(text: String) {
        Log.d("dd33", "update text inside choiceButton")
        this.text = text
    }

    override fun onSaveInstanceState(): Parcelable? {
        Log.d("dd33", "onSaveInstanceState inside choiceButton")
        return super.onSaveInstanceState()?.let {
            val choiceButtonSaveState = ChoiceButtonSaveState(it)
            choiceButtonSaveState.save(state)
            return choiceButtonSaveState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        Log.d("dd33", "onRestoreInstanceState inside choiceButton")
        val savedState = state as ChoiceButtonSaveState
        super.onRestoreInstanceState(savedState.superState)
        update(savedState.restore())
    }


    override fun getFreezesText(): Boolean = true
}

interface UpdateChoiceButton : UpdateText {
    fun update(state: ChoiceUiState)
    fun update(color: String,
                clickable: Boolean,
                isEnabled: Boolean)
}
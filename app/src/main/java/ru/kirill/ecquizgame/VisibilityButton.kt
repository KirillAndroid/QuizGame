package ru.kirill.ecquizgame

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class VisibilityButton : AppCompatButton, UpdateVisibility {
    private lateinit var state: VisibilityState

    constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()?.let {
                val VisibilitySavedState = VisibilityStateSaved(it)
                VisibilitySavedState.save(state)
                return VisibilitySavedState
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val savedState = state as VisibilityStateSaved
            super.onRestoreInstanceState(savedState.superState)
            update(savedState.restore())
        }

    override fun update(restore: VisibilityState) {
        state = restore
        state.update(this)
    }

    override fun update(visibility: Int) {
        setVisibility(visibility)
//        state.update(this)
    }
}

interface UpdateVisibility {
    fun update(restore: VisibilityState)
    fun update(visibility: Int)

}

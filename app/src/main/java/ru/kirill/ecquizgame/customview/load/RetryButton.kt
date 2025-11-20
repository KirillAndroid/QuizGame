package ru.kirill.ecquizgame.customview.load

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import ru.kirill.ecquizgame.customview.game.UpdateVisibility
import ru.kirill.ecquizgame.customview.game.VisibilityState
import ru.kirill.ecquizgame.customview.game.VisibilityStateSaved

class RetryButton : AppCompatButton, UpdateVisibility {
    private lateinit var state: VisibilityState
    constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()?.let {
                val VisibilityStateSaved = VisibilityStateSaved(it)
                VisibilityStateSaved.save(state)
                return VisibilityStateSaved
            }
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            val savedState = state as VisibilityStateSaved
            super.onRestoreInstanceState(savedState.superState)
            update(savedState.restore())
        }

    override fun update(restore: VisibilityState) {
        state = restore
        restore.update(this)
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }

}
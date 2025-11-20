package ru.kirill.ecquizgame.customview.game

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import java.io.Serializable

class VisibilityStateSaved : View.BaseSavedState {

    private lateinit var state: VisibilityState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        Log.d("dd33", "constructor inside choiceButtonSaveState")
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(VisibilityState::class.java.classLoader, VisibilityState::class.java) as VisibilityState
        } else {
            parcelIn.readSerializable() as VisibilityState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() = state

    fun save(state: VisibilityState) {
        this.state = state
    }


    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VisibilityStateSaved> {
        override fun createFromParcel(parcel: Parcel): VisibilityStateSaved {
            return VisibilityStateSaved(parcel)
        }

        override fun newArray(size: Int): Array<VisibilityStateSaved?> {
            return arrayOfNulls(size)
        }
    }
}

data class VisibilityState(private val visibility: Int) : Serializable {
    fun update(updateVisibility: UpdateVisibility) {
        updateVisibility.update(visibility)
    }
}
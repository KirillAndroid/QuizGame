package ru.kirill.ecquizgame.customview.load

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class ErrorStateSaved : View.BaseSavedState {

    private lateinit var state: ErrorState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(ErrorState::class.java.classLoader, ErrorState::class.java) as ErrorState
        } else {
            parcelIn.readSerializable() as ErrorState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() = state

    fun save(state: ErrorState) {
        this.state = state
    }


    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ErrorStateSaved> {
        override fun createFromParcel(parcel: Parcel): ErrorStateSaved {
            return ErrorStateSaved(parcel)
        }

        override fun newArray(size: Int): Array<ErrorStateSaved?> {
            return arrayOfNulls(size)
        }
    }
}


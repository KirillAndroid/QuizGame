package ru.kirill.ecquizgame.customview

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import ru.kirill.ecquizgame.ChoiceUiState

class ChoiceButtonSaveState : View.BaseSavedState {

    private lateinit var state: ChoiceUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        Log.d("dd33", "constructor inside choiceButtonSaveState")
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(ChoiceUiState::class.java.classLoader, ChoiceUiState::class.java) as ChoiceUiState
        } else {
            parcelIn.readSerializable() as ChoiceUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        Log.d("dd33", "writeToParcel inside choiceButtonSaveState")
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() : ChoiceUiState {
        Log.d("dd33", "restore inside choiceButtonSaveState")
        return state
    }

    fun save(state: ChoiceUiState) {
        Log.d("dd33", "save inside choiceButtonSaveState")
        this.state = state
    }


    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ChoiceButtonSaveState> {
        override fun createFromParcel(parcel: Parcel): ChoiceButtonSaveState {
            Log.d("dd33", "createFromParcel inside choiceButtonSaveState")
            return ChoiceButtonSaveState(parcel)
        }

        override fun newArray(size: Int): Array<ChoiceButtonSaveState?> {
            Log.d("dd33", "newArray inside choiceButtonSaveState")
            return arrayOfNulls(size)
        }
    }
}
package ru.kirill.ecquizgame

import android.content.SharedPreferences

interface IntCashe {
    fun read(defaultValue: Int) : Int
    fun save(newValue: Int)

    class Base(val sharedPreferences: SharedPreferences, val name: String) : IntCashe {
        override fun read(defaultValue: Int): Int {
            return sharedPreferences.getInt(name, defaultValue)
        }

        override fun save(newValue: Int) {
            sharedPreferences.edit().putInt(name, newValue).apply()
        }

    }

}

package ru.kirill.ecquizgame.di

import android.content.Context

class Core(context: Context, val clear: ClearViewModel) {
    val sharedPreferences = context.getSharedPreferences("quizGameDate", Context.MODE_PRIVATE)
}
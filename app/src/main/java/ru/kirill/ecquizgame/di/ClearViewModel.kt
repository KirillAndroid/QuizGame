package ru.kirill.ecquizgame.di

import ru.kirill.ecquizgame.fragments.MyViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}
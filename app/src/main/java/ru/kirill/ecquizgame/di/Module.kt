package ru.kirill.ecquizgame.di

import ru.kirill.ecquizgame.fragments.MyViewModel

interface Module<T : MyViewModel> {
    fun viewModel() : T
}
package ru.kirill.ecquizgame.di

import ru.kirill.ecquizgame.fragments.MyViewModel

interface ManageViewModel : ProvideViewModel, ClearViewModel {

    class Factory(private val make: ProvideViewModel) : ManageViewModel {
        private val viewModelMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun <T : MyViewModel> makeViewModel(claszz: Class<T>): T {
            if (viewModelMap[claszz] == null) {
                val viewModel = make.makeViewModel(claszz)
                viewModelMap[claszz] = viewModel
                return viewModel
            } else {
                return viewModelMap[claszz] as T
            }


        }

        override fun clear(viewModelClass: Class<out MyViewModel>) {
            viewModelMap[viewModelClass] = null
        }

    }

}
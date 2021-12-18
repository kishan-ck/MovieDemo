package com.coderkube.movieapp.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coderkube.movieapp.viewModel.MovieListViewModel


/**
 * This class is use for movie list viewmodel factory
 * */

class MovieListViewModelFactory (private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(application) as T
    }
}
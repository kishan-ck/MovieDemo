package com.coderkube.movieapp.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coderkube.movieapp.viewModel.MovieDetailViewModel

/**
 * This class is use for movie Detail viewmodel factory
 * */

class MovieDetailViewModelFactory  (private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(application) as T
    }
}
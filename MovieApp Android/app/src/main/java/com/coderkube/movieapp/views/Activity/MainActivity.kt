package com.coderkube.movieapp.views.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderkube.movieapp.Adapter.MoviePackageAdapter
import com.coderkube.movieapp.R
import com.coderkube.movieapp.Utils.utils
import com.coderkube.movieapp.viewModel.MovieListViewModel
import com.coderkube.movieapp.viewModelFactory.MovieListViewModelFactory
import com.coderkube.movieapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowManager

import android.os.Build
import android.view.Window


class MainActivity : AppCompatActivity() {

    /**
     * use for a data binding
     * */
    private var binding: ActivityMainBinding? = null

    /**
     * use for a set viewmodel factory
     * */
    private val movieViewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(application)
    }

    var moviePackageAdapter: MoviePackageAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this
        binding?.movieViewModel = movieViewModel

        setUpObservers()
    }


    /**
     * This function use for observed
     * */
    private fun setUpObservers() {

        /**
         * set api calling
         * */
        utils.Util.ShowProgress(this)
        movieViewModel.MovieListCall()

        /**
         * use for movie list response
         * */
        movieViewModel.MovieListResponse.observe(
            this, { movieData ->

                utils.Util.HideProgress()
                Log.e("TAG", "setUpObservers:${movieData} ")

                recyclerviewMovieList.setLayoutManager(
                    LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                )
                moviePackageAdapter = MoviePackageAdapter(this, movieData)
                recyclerviewMovieList?.setAdapter(moviePackageAdapter)
            })
    }
}
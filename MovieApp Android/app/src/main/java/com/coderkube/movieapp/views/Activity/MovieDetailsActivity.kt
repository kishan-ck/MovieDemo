package com.coderkube.movieapp.views.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.coderkube.movieapp.R
import com.coderkube.movieapp.Utils.utils
import com.coderkube.movieapp.databinding.ActivityMovieDetailsBinding
import com.coderkube.movieapp.viewModel.MovieDetailViewModel
import com.coderkube.movieapp.viewModelFactory.MovieDetailViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.toolbar.*


class MovieDetailsActivity : AppCompatActivity() {

    /**
     * use for a data binding
     * */
    private var binding: ActivityMovieDetailsBinding? = null

    /**
     * use for a set viewmodel factory
     * */
    private val movieDetailViewModel: MovieDetailViewModel by viewModels {
        MovieDetailViewModelFactory(application)
    }

    var genres: String? = null
    var Language: String? = null
    var ProductionCompany: String? = null
    var genresList: ArrayList<String> = ArrayList()
    var movieLanguageList: ArrayList<String> = ArrayList()
    var movieProductionList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding?.lifecycleOwner = this
        binding?.movieDetailViewModel = movieDetailViewModel

        toolbar_title.text = intent.getSerializableExtra("originalTitle").toString()

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
        movieDetailViewModel.MovieDetailCall()

        /**
         * use for movie details response and set data in view
         * */
        movieDetailViewModel.MovieDetailResponse.observe(
            this, { movieData ->

                utils.Util.HideProgress()
                Log.e("TAG", "setUpObservers:${movieData} ")

                movieDetailViewModel.movieName.value = movieData.originalTitle
                movieDetailViewModel.movieDate.value = intent.getStringExtra("releaseDate")
                movieDetailViewModel.movieRate.value = movieData.voteAverage.toString()
                movieDetailViewModel.moviedetail.value = movieData.overview

                Glide.with(application)
                    .load(movieData.posterPath)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(movieImageView)

                Glide.with(application)
                    .load(movieData.backdropPath)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(movieImage)


                for (i in 0..movieData.genres.size - 1) {
                    genresList.add(movieData.genres.get(i).name)
//                    android.text.TextUtils.join(",", genresList)
//                    StringBuilder().apply { append(",")}

                }

                for (i in 0..movieData.spokenLanguages.size - 1) {
                    movieLanguageList.add(movieData.spokenLanguages.get(i).name)
                }

                for (i in 0..movieData.productionCompanies.size - 1) {
                    movieProductionList.add(movieData.productionCompanies.get(i).name)
                }

                genres = genresList.toString()
                genres = genres!!.replace("[", "").replace("]", "")

                Language = movieLanguageList.toString()
                Language = Language!!.replace("[", "").replace("]", "")

                ProductionCompany = movieProductionList.toString()
                ProductionCompany = ProductionCompany!!.replace("[", "").replace("]", "")

                movieDetailViewModel.movieGenres.value = genres

                movieDetailViewModel.movieLanguages.value = Language

                movieDetailViewModel.movieProduction.value = ProductionCompany

            })

        /**
         * use for set error
         * */
        movieDetailViewModel.errorMessage.observe(this, { error ->
            utils.Util.HideProgress()
            utils.Util.DebugToast(this, error)
        })
    }
}
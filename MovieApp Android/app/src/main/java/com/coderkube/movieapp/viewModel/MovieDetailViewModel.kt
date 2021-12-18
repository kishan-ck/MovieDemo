package com.coderkube.movieapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coderkube.movieapp.Models.MovieDetailModel
import com.coderkube.movieapp.R
import com.coderkube.movieapp.Utils.App
import com.coderkube.movieapp.Utils.utils
import com.coderkube.movieapp.repository.MovieDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(application: Application) : AndroidViewModel(application)  {

    val errorMessage = MutableLiveData<String>()
    val movieName = MutableLiveData<String>()
    val movieDate = MutableLiveData<String>()
    val movieRate = MutableLiveData<String>()
    val movieGenres = MutableLiveData<String>()
    val movieLanguages = MutableLiveData<String>()
    val movieProduction = MutableLiveData<String>()
    val moviedetail = MutableLiveData<String>()
    var movieDetailModel : MovieDetailModel? = null
    val MovieDetailResponse = MutableLiveData<MovieDetailModel>()


    /**
     * this function is use for Api calling and check response
     * */
    fun MovieDetailCall() {
        if (utils.Util.isConnect(getApplication())) {

            val apikey="c9856d0cb57c3f14bf75bdc6c063b8f3"

            Log.e("movielistId2","list id2>>"+utils.Util.movieListID)

            CoroutineScope(Dispatchers.IO).launch {
                val response = MovieDetailRepository.MovieDetails(utils.Util.movieListID.toString(),apikey)

                withContext(Dispatchers.Main) {

                    movieDetailModel = response.body()

                    if (response.isSuccessful) {
                        when (response.code()) {
                            200 -> {
                                if (response.body() != null) {



                                    MovieDetailResponse.value = movieDetailModel

                                    Log.e("countryresponseeeee2>>>",""+MovieDetailResponse.value)


                                }
                            }

                        }
                    } else
                        when (response.code()) {
                            500 -> setError(R.string.server_error)
                            else -> setError(R.string.something_went_wrong)
                        }
                }
            }
        }
    }


    /**
     * this function is use for check error
     * */
    private fun setError(text: Any) {
        if (text is Int)
            errorMessage.postValue(getApplication<App>().getString(text))
        else
            errorMessage.postValue(text.toString())
    }
}
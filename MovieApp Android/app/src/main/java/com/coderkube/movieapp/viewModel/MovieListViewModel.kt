package com.coderkube.movieapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coderkube.movieapp.Models.MovieList
import com.coderkube.movieapp.R
import com.coderkube.movieapp.Utils.App
import com.coderkube.movieapp.Utils.utils
import com.coderkube.movieapp.repository.MovieListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(application: Application) :AndroidViewModel(application) {


    val MovieListResponse = MutableLiveData<ArrayList<MovieList.Result>>()
    val errorMessage = MutableLiveData<String>()
    var movieModel : MovieList ? = null

    /**
     * this function is use for Api calling and check response
     * */
    fun MovieListCall() {
        if (utils.Util.isConnect(getApplication())) {

            val apikey="c9856d0cb57c3f14bf75bdc6c063b8f3"


            CoroutineScope(Dispatchers.IO).launch {
                val response = MovieListRepository.MovieListSelect(apikey)

                withContext(Dispatchers.Main) {

                    movieModel = response.body()

                    if (response.isSuccessful) {
                        when (response.code()) {
                            200 -> {
                                if (response.body() != null) {



                                    MovieListResponse.value = movieModel?.results

                                        Log.e("countryresponseeeee",""+MovieListResponse.value)


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
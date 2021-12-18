package com.coderkube.movieapp.repository

import com.coderkube.events.WebService.ApiClient


/**
 * This class is use for a movie details repository
 * */


object MovieDetailRepository {

    suspend fun MovieDetails(movie_id: String, apikey: String) = ApiClient.getClient().MovieDetails(
        movie_id,
        apikey
    )
}
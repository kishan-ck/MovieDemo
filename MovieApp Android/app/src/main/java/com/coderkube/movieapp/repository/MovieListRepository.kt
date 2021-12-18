package com.coderkube.movieapp.repository

import com.coderkube.events.WebService.ApiClient

/**
 * This class is use for a movie list repository
 * */

object MovieListRepository {

    suspend fun MovieListSelect(apikey: String) = ApiClient.getClient().MovieList(
        apikey
    )
}
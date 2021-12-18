package com.coderkube.events.WebService


import com.coderkube.events.WebService.ApiConstant.MOVIEDETAILS
import com.coderkube.events.WebService.ApiConstant.MOVIELIST
import com.coderkube.movieapp.Models.MovieDetailModel
import com.coderkube.movieapp.Models.MovieList
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {


    @GET(MOVIELIST)
    suspend fun MovieList(
        @Query("api_key") apiKey:String
    ): Response<MovieList>

    @GET(MOVIEDETAILS)
    suspend fun MovieDetails(
        @Path("movie_id") movie_id:String,
        @Query("api_key") apiKey:String
    ): Response<MovieDetailModel>


}
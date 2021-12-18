package com.coderkube.movieapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coderkube.movieapp.Models.MovieList
import com.coderkube.movieapp.R
import com.coderkube.movieapp.Utils.utils
import com.coderkube.movieapp.views.Activity.MovieDetailsActivity
import kotlinx.android.synthetic.main.recyclerview_movie_list.view.*

/**
 * use foe a set data for a movie list
* */

class MoviePackageAdapter(
    var context: Context,
    var moviePackageArrayLiat: ArrayList<MovieList.Result>
) : RecyclerView.Adapter<MoviePackageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_movie_list, parent, false)
        return ViewHolder(view)
    }

    /**
     * use for a set data binding and set data in view
    * */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var movideListModel: MovieList.Result = moviePackageArrayLiat.get(position)!!

        var originalTitle: String? = movideListModel.originalTitle
        var releaseDate: String? = movideListModel.releaseDate
        var voteAverage: String? = movideListModel.voteAverage.toString()
        var overview: String? = movideListModel.overview
        var posterPath: String? = movideListModel.posterPath

        holder.movieNameTextView.text = originalTitle
        holder.movieDateTextView.text = releaseDate
        holder.movierateTextView.text = voteAverage
        holder.movieDetailsTextView.text = overview

        Glide.with(context)
            .load(posterPath)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.moviePicImageView)

        holder.movieListLayout.setOnClickListener(View.OnClickListener {
//            onItemClick.onitemclicked(countryListModel)
            utils.Util.movieListID = movideListModel.id.toString()
            var intent = Intent(context , MovieDetailsActivity::class.java)
            intent.putExtra("releaseDate",releaseDate)
            intent.putExtra("originalTitle",originalTitle)
            context.startActivity(intent)

            Log.e("movielistId","list id>>"+utils.Util.movieListID)
        })
    }

    override fun getItemCount(): Int {
        return moviePackageArrayLiat.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var movieDetailsTextView = itemView.movieDetailsTextView
        var movierateTextView = itemView.movierateTextView
        var movieDateTextView = itemView.movieDateTextView
        var movieNameTextView = itemView.movieNameTextView
        var moviePicImageView = itemView.moviePicImageView
        var movieListLayout = itemView.movieListLayout

    }

}
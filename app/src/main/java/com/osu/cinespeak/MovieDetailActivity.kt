package com.osu.cinespeak

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MovieDetailActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviedetail)

        var movie: Map<String, Any>? = null

        val backArrow = findViewById<ImageView>(R.id.back)
        backArrow.setOnClickListener {
            // remove this activity to reveal MainActviity again
        }

        if(intent.hasExtra(MainActivity.DETAIL_SCREEN)) {
            movie = intent.getSerializableExtra(MainActivity.DETAIL_SCREEN) as Map<String, Any>
        }

        if(movie != null) {
            updateUI(movie)
        }
    }

    private fun updateUI(movie: Map<String, Any?>) {
        // get layout's views to show movie details
        val moviePoster = findViewById<ImageView>(R.id.movie_poster)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val movieGenre = findViewById<TextView>(R.id.movie_genre)
        val movieRating = findViewById<TextView>(R.id.movie_rating)
        val movieOverview = findViewById<TextView>(R.id.movie_overview)

        // Get details from movie Map
        val overview = movie["overview"].toString()
        val posterURL = movie["poster_path"].toString()
        val title = movie["title"].toString()
        val genres = movie["genre"] as List<String>
        val rating = movie["rating"].toString()
        var genreText = ""

        for(i in 0..<genres.size) {
            genreText += genres[i] + " "
        }

        // Update UI with movie details
        Glide.with(this@MovieDetailActivity)
            .load(posterURL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(moviePoster)

        movieTitle.text = title
        movieGenre.text = genreText
        movieRating.text = rating
        movieOverview.text = overview
    }
}
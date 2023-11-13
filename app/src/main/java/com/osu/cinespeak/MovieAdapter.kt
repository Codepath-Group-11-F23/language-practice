package com.osu.cinespeak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(val movieCollection: Array<Map<String, String>>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val movieImage: ImageView
        val favImage: ImageView
        val movieTitle: TextView
        val movieGenre: TextView
        val movieRating: TextView

        init {
            movieImage = view.findViewById(R.id.posterView)
            favImage = view.findViewById(R.id.favView)
            movieTitle = view.findViewById(R.id.cardTitle)
            movieGenre = view.findViewById(R.id.cardGenre)
            movieRating = view.findViewById(R.id.cardRating)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movieCollection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = movieCollection[position]["title"]
        holder.movieRating.text = movieCollection[position]["rating"] + "/10"
        holder.movieGenre.text = movieCollection[position]["genre"]

        Glide.with(holder.itemView)
            .load(movieCollection[position]["poster_path"])
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(holder.movieImage)

    }
}
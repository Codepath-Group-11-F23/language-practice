package com.osu.cinespeak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(val movieCollection: Array<Map<String, Any>>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {

    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(position: Int, movie: Map<String, Any>)
    }

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
        val genreStringList = movieCollection[position]["genre"] as List<String>
        var genreText = ""
        holder.movieTitle.text = movieCollection[position]["title"].toString()
        holder.movieRating.text = movieCollection[position]["rating"].toString() + "/10"

        for(i in 0..<genreStringList.size) {
            genreText += genreStringList[i] + " "
        }
        holder.movieGenre.text = genreText

        Glide.with(holder.itemView)
        .load(movieCollection[position]["poster_path"].toString())
        .placeholder(R.drawable.ic_launcher_foreground)
        .centerCrop()
        .into(holder.movieImage)

        holder.itemView.setOnClickListener {
            if(onClickListener != null) {
                onClickListener!!.onClick(position, movieCollection[position])
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
}
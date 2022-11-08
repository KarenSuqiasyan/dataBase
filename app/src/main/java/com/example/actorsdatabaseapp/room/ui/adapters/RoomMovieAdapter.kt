package com.example.actorsdatabaseapp.room.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsdatabaseapp.databinding.ItemMovieBinding
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom

class RoomMovieAdapter(private val itemClickListener: (ActionEnum, MovieRoom) -> Unit) : RecyclerView.Adapter<RoomMovieAdapter.BaseViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var context: Context
    var movies: MutableList<MovieRoom> = mutableListOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder = MovieItemViewHolder(ItemMovieBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(items: List<MovieRoom>) {
        this.movies.clear()
        this.movies.addAll(items)
        notifyDataSetChanged()
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: MovieRoom)
    }

    inner class MovieItemViewHolder(private var binding: ItemMovieBinding) : BaseViewHolder(binding.root) {
        init {
            binding.deleteMovieImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_DELETE_MOVIE, movies[adapterPosition]) }
        }

        @SuppressLint("SetTextI18n")
        override fun bind(item: MovieRoom) {
            item.let {
                    val movieName = it.movieName
                    val rate = it.imdbRate
                    val year = it.year
                    val actorId = it.actorId
                    binding.itemMovieNameTextView.text = "movie name: $movieName"
                    binding.itemMovieRateTextview.text = "IMDB Rate: $rate"
                    binding.itemMovieYearTextview.text = "movie Year: $year"
                    binding.itemActorIdTextview.text = "Actor ID: $actorId"
                    binding.itemActorNameTextview.isVisible = false
            }
        }
    }


    enum class ActionEnum {
        ACTION_DELETE_MOVIE
    }
}
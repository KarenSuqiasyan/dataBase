package com.example.actorsdatabaseapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsdatabaseapp.databinding.ItemActorBinding
import com.example.actorsdatabaseapp.data.model.Actor

class ActorsAdapter(private val itemClickListener: (ActionEnum, Actor) -> Unit) : RecyclerView.Adapter<ActorsAdapter.BaseViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var context: Context
    var actors: MutableList<Actor> = mutableListOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsAdapter.BaseViewHolder = ActorItemViewHolder(ItemActorBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(holder: ActorsAdapter.BaseViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount() = actors.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(items: MutableList<Actor>) {
        this.actors.clear()
        this.actors.addAll(items)
        notifyDataSetChanged()
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: Actor)
    }

    inner class ActorItemViewHolder(private var binding: ItemActorBinding) : BaseViewHolder(binding.root) {
        init {
            binding.deleteActorImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_DELETE, actors[adapterPosition]) }
            binding.addMovieImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_ADD_MOVIE, actors[adapterPosition]) }
        }

        override fun bind(item: Actor) {
            item.let {
                binding.itemAgeTextview.text = item.age
                binding.itemNameTextView.text = item.name
                binding.itemSurnameTextview.text = item.surName
            }
        }
    }

    enum class ActionEnum {
        ACTION_DELETE,
        ACTION_ADD_MOVIE
    }
}

package com.example.actorsdatabaseapp.room.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsdatabaseapp.databinding.ItemActorBinding
import com.example.actorsdatabaseapp.room.data.model.ActorRoom


class RoomActorsAdapter(private val itemClickListener: (ActionEnum, ActorRoom) -> Unit) : RecyclerView.Adapter<RoomActorsAdapter.BaseViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var context: Context
    var actors: MutableList<ActorRoom> = mutableListOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomActorsAdapter.BaseViewHolder = ActorItemViewHolder(ItemActorBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(holder: RoomActorsAdapter.BaseViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount() = actors.size

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: ActorRoom)
    }

    inner class ActorItemViewHolder(private var binding: ItemActorBinding) : RoomActorsAdapter.BaseViewHolder(binding.root) {

        init {
            binding.deleteActorImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_DELETE, actors[adapterPosition]) }
            binding.addMovieImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_ADD_MOVIE, actors[adapterPosition]) }
        }

        override fun bind(item: ActorRoom) {
            item.let {
                binding.itemAgeTextview.text = item.age.toString()
                binding.itemNameTextView.text = item.name
                binding.itemSurnameTextview.text = item.surName
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(items: List<ActorRoom>) {
        this.actors.clear()
        this.actors.addAll(items)
        notifyDataSetChanged()
    }

    enum class ActionEnum {
        ACTION_DELETE,
        ACTION_ADD_MOVIE
    }
}
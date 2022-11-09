package com.example.actorsdatabaseapp.sql.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsdatabaseapp.databinding.ItemActorBinding
import com.example.actorsdatabaseapp.room.data.model.MovieRoom
import com.example.actorsdatabaseapp.sql.data.model.Actor
import com.example.actorsdatabaseapp.sql.data.model.Pets

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
            binding.itemPetsAgeTextview.isVisible = false
            binding.itemPetsNameTextview.isVisible = false
            binding.itemIsSmartCheckBox.isVisible = false
            binding.itemIsSmartTextView.isVisible = false
            item.let {
                item.pets.forEach {
                    val name = it.name
                    val age = it.age
                    val isSmart = it.isSmart
                    binding.itemPetsAgeTextview.text = age.toString()
                    binding.itemPetsNameTextview.text = name
                    binding.itemIsSmartCheckBox.isChecked = isSmart
                    binding.itemPetsAgeTextview.isVisible = true
                    binding.itemPetsNameTextview.isVisible = true
                    binding.itemIsSmartCheckBox.isVisible = true
                    binding.itemIsSmartTextView.isVisible = true
                }
                binding.itemAgeTextview.text = item.age.toString()
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

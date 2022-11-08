package com.example.actorsdatabaseapp.room.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder = ActorItemViewHolder(ItemActorBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount() = actors.size

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: ActorRoom)
    }

    inner class ActorItemViewHolder(private var binding: ItemActorBinding) : BaseViewHolder(binding.root) {

        init {
            binding.deleteActorImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_DELETE, actors[adapterPosition]) }
            binding.addMovieImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_ADD_MOVIE, actors[adapterPosition]) }
            binding.addPetImageView.setOnClickListener { itemClickListener(ActionEnum.ACTION_ADD_PET, actors[adapterPosition]) }
        }

        override fun bind(item: ActorRoom) {
            binding.itemPetsAgeTextview.isVisible = false
            binding.itemPetsNameTextview.isVisible = false
            binding.itemIsSmartCheckBox.isVisible = false
            binding.itemIsSmartTextView.isVisible = false
            item.let {
                item.pets?.forEach {
                    val name = it.petName
                    val age = it.petAge
                    val isSmart = it.petIsSmart
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(items: List<ActorRoom>) {
        this.actors.clear()
        this.actors.addAll(items)
        notifyDataSetChanged()
    }

    enum class ActionEnum {
        ACTION_DELETE,
        ACTION_ADD_MOVIE,
        ACTION_ADD_PET
    }
}
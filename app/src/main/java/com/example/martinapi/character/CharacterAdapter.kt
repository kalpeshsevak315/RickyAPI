package com.example.martinapi.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.martinapi.data.entities.Characters
import com.example.martinapi.databinding.ItemCharacterBinding

/** Click
 * https://www.section.io/engineering-education/handling-recyclerview-clicks-the-right-way/
 */
class CharacterAdapter(private val onClickListener: OnClickListener) : ListAdapter<Characters, CharacterAdapter.MyViewHolder>(SampleItemDiffCallback()){
    class OnClickListener(val clickListener: (meme: Characters) -> Unit) {
        fun onClick(meme: Characters) = clickListener(meme)
    }


    class MyViewHolder(val itemBinding: ItemCharacterBinding) : RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        // viewbinding
        val binding = ItemCharacterBinding.inflate(view, parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val peopleList = getItem(position)

       // holder.itemBinding.textView.text = peopleList.firstName
        holder.itemBinding.name.text = peopleList.name
        holder.itemView.setOnClickListener {
            onClickListener.onClick(peopleList)
        }

        // Image loading
        Glide.with(holder.itemView.context)
            .load(peopleList.image)
            .into(holder.itemBinding.image)

    }

}

class SampleItemDiffCallback : DiffUtil.ItemCallback<Characters>() {
    override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean = oldItem == newItem

}
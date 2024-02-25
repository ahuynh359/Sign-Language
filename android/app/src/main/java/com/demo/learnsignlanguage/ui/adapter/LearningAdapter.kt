package com.demo.learnsignlanguage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.learnsignlanguage.databinding.ItemLearningBinding
import com.demo.learnsignlanguage.utils.LearningData


class LearningAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<LearningAdapter.ViewHolder>() {

    private val items = mutableListOf<LearningData>(
        LearningData.ASL,
        LearningData.Num,
        LearningData.Alphabet,
        LearningData.Word
    )

    inner class ViewHolder(private val binding: ItemLearningBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    listener.onItemClick(adapterPosition, items[adapterPosition])
            }
        }

        fun bind(item: LearningData) {

            binding.apply {
                binding.imvPhoto.setImageResource(item.image)
                binding.tvName.text = item.nameCard
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLearningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: LearningData)
    }

}
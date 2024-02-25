package com.demo.learnsignlanguage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.learnsignlanguage.data.model.Message
import com.demo.learnsignlanguage.databinding.ItemMessageBinding

class MessageAdapter(
    private val messageList: MutableList<Message>,
    private val listener: OnMessageClicked
) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {


    inner class ViewHolder(val itemBinding: ItemMessageBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messageList[position]

        holder.itemView.setOnClickListener {
            listener.handle(position)
        }

        holder.itemBinding.apply {
            timeText.text = message.time
            messageText.text = message.message
        }

    }


    override fun getItemCount(): Int = messageList.size
}

class MessageDiffCallback : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.message == newItem.message
    }
}

interface OnMessageClicked {
    fun handle(position: Int)
}
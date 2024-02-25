package com.demo.learnsignlanguage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.learnsignlanguage.R
import com.demo.learnsignlanguage.data.model.TempUser
import com.demo.learnsignlanguage.databinding.ItemUserBinding

class UserAdapter(
    private val userList: MutableList<TempUser>,
    private val listener: OnItemClicked
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    inner class ViewHolder(val itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.itemView.setOnClickListener {
            listener.handle(position)
        }

        holder.itemBinding.apply {
            if (user.permission == 0) {
                imvAvatat.setImageResource(R.drawable.student)
                tvCat.text = "Student"
            } else {
                imvAvatat.setImageResource(R.drawable.teacher)
                tvCat.text = "Teacher"
            }
            tvName.text = user.name
        }

    }


    override fun getItemCount(): Int = userList.size
}

interface OnItemClicked {
    fun handle(position: Int)
}
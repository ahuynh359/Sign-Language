package com.demo.learnsignlanguage.ui.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.demo.learnsignlanguage.R
import com.demo.learnsignlanguage.data.api.CardApi
import com.demo.learnsignlanguage.databinding.ItemCardBinding
import com.squareup.picasso.Picasso

class CardAdapter(
    private val listCard: MutableList<CardApi>
) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {




    inner class ViewHolder(val itemBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = listCard[position]

        holder.itemBinding.apply {
//            if (card.front.isNotEmpty())
            Picasso.get().load(listCard[position].front).fit()
                .into(imv)
            tv.text = listCard[position].back
        }
        holder.itemBinding.apply {
            holder.itemView.setOnClickListener {
                if (card.isFront) {
                    val oa1 = ObjectAnimator.ofFloat(cardHolder, "scaleX", 1f, 0f)
                    val oa2 = ObjectAnimator.ofFloat(cardHolder, "scaleX", 0f, 1f)
                    oa1.interpolator = AccelerateInterpolator()
                    oa2.interpolator = DecelerateInterpolator()
                    oa1.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            front.visibility = View.GONE
                            back.visibility = View.VISIBLE
                            oa2.start()
                        }
                    })
                    oa1.start()
                } else {
                    val oa1 = ObjectAnimator.ofFloat(cardHolder, "scaleX", 1f, 0f)
                    val oa2 = ObjectAnimator.ofFloat(cardHolder, "scaleX", 0f, 1f)
                    oa1.interpolator = AccelerateInterpolator()
                    oa2.interpolator = DecelerateInterpolator()
                    oa1.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            back.visibility = View.GONE
                            front.visibility = View.VISIBLE
                            oa2.start()
                        }
                    })
                    oa1.start()
                }
                card.isFront = !card.isFront
            }
        }

    }
    fun shuffleItems() {
        listCard.shuffle()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listCard.size
}

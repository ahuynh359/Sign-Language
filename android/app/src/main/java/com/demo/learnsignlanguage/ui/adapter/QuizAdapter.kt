package com.demo.learnsignlanguage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.learnsignlanguage.R
import com.demo.learnsignlanguage.data.model.Question
import com.demo.learnsignlanguage.databinding.ItemQuizBinding
import com.squareup.picasso.Picasso

class QuizAdapter(
    private val questionList: MutableList<Question>,
    private val listener: AnswerSelectionListener
) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {


    inner class ViewHolder(val itemBinding: ItemQuizBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questionList[position]


        holder.itemBinding.apply {

            Picasso.get().load(question.image).fit()
                .into(imv)
            a.text = question.ans[0]
            b.text = question.ans[1]
            c.text = question.ans[2]
            d.text = question.ans[3]
            holder.itemBinding.a.setOnClickListener {
                if (question.correctAns == 0) {
                    a.setBackgroundResource(R.drawable.custom_tv_correct)
                    listener.onAnswerSelected(true)

                } else {
                    a.setBackgroundResource(R.drawable.custom_tv_wrong)
                    listener.onAnswerSelected(false)
                }
                when (question.correctAns) {
                    0 -> {
                        a.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    1 -> {
                        b.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    2 -> {
                        c.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    3 -> {
                        d.setBackgroundResource(R.drawable.custom_tv_correct)
                    }
                }
            }


            holder.itemBinding.b.setOnClickListener {
                if (question.correctAns == 1) {
                    b.setBackgroundResource(R.drawable.custom_tv_correct)
                    listener.onAnswerSelected(true)

                } else {
                    b.setBackgroundResource(R.drawable.custom_tv_wrong)
                    listener.onAnswerSelected(false)
                }
                when (question.correctAns) {
                    0 -> {
                        a.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    1 -> {
                        b.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    2 -> {
                        c.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    3 -> {
                        d.setBackgroundResource(R.drawable.custom_tv_correct)
                    }
                }
            }

            holder.itemBinding.c.setOnClickListener {
                if (question.correctAns == 2) {
                    c.setBackgroundResource(R.drawable.custom_tv_correct)
                    listener.onAnswerSelected(true)

                } else {
                    c.setBackgroundResource(R.drawable.custom_tv_wrong)
                    listener.onAnswerSelected(false)
                }
                when (question.correctAns) {
                    0 -> {
                        a.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    1 -> {
                        b.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    2 -> {
                        c.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    3 -> {
                        d.setBackgroundResource(R.drawable.custom_tv_correct)
                    }
                }
            }

            holder.itemBinding.d.setOnClickListener {
                if (question.correctAns == 3) {
                    d.setBackgroundResource(R.drawable.custom_tv_correct)
                    listener.onAnswerSelected(true)

                } else {
                    d.setBackgroundResource(R.drawable.custom_tv_wrong)
                    listener.onAnswerSelected(false)
                }
                when (question.correctAns) {
                    0 -> {
                        a.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    1 -> {
                        b.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    2 -> {
                        c.setBackgroundResource(R.drawable.custom_tv_correct)
                    }

                    3 -> {
                        d.setBackgroundResource(R.drawable.custom_tv_correct)
                    }
                }
            }
        }


    }


    fun shuffleItems() {
        questionList.shuffle()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = questionList.size
}

interface AnswerSelectionListener {
    fun onAnswerSelected(isCorrect: Boolean)
}

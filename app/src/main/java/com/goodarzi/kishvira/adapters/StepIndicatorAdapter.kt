package com.goodarzi.kishvira.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.goodarzi.kishvira.R
import com.goodarzi.kishvira.model.Steps
import com.goodarzi.kishvira.model.fire.Fire
import kotlinx.android.synthetic.main.item_steps.view.*

class StepIndicatorAdapter(
    val context: Context,
    val fire: Fire
) :
    RecyclerView.Adapter<StepsViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewholder {
        return StepsViewholder(
            LayoutInflater.from(context).inflate(
                R.layout.item_steps,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return fire.data.history.size
    }

    override fun onBindViewHolder(holder: StepsViewholder, position: Int) {
        holder.view.item_steps_date.text = fire.data.history[position].createDateString
        holder.view.item_steps_desc.text = fire.data.history[position].statusTitle
        if (position == (fire.data.history.size - 1)) {
            holder.view.item_steps_pic.background =
                context.getDrawable(R.drawable.ic_circle_outline)
            holder.view.item_steps_indicator.visibility = View.GONE
        }

    }


}

class StepsViewholder(val view: View) : RecyclerView.ViewHolder(view)
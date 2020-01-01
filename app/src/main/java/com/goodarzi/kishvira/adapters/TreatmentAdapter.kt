package com.goodarzi.kishvira.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.goodarzi.kishvira.R
import com.goodarzi.kishvira.model.Treatment.Requests
import com.goodarzi.kishvira.utilz.Interfaces
import kotlinx.android.synthetic.main.item_cure.view.*


class TreatmentAdapter(private val onHistoryClicked: Interfaces.getDialog) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Requests>() {

        override fun areItemsTheSame(oldItem: Requests, newItem: Requests): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Requests, newItem: Requests): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cure,
                parent,
                false
            ),onHistoryClicked
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Requests>) {
        differ.submitList(list)
    }

    class ViewHolder
    constructor(itemView: View,val onHistoryClicked: Interfaces.getDialog) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Requests) = with(itemView) {


            itemView.cure_type.text = item.treatmentRiskName
            itemView.cure_date.text = item.lossDateTime
            itemView.cure_price.text = item.amountByInsured.toString()
            itemView.cure_id.text = " کد درخواست : ${item.id}"

            itemView.cure_history.setOnClickListener {
                val popup = PopupMenu(context, it)
                val inflater = popup.menuInflater
                inflater.inflate(R.menu.actions, popup.menu)
                popup.show()
                popup.setOnMenuItemClickListener { it1 ->
                    if (it1.itemId == R.id.menu_history) {
                        onHistoryClicked.setUpDialog(item.id)
                    }
                    return@setOnMenuItemClickListener true
                }

            }

        }
    }


}

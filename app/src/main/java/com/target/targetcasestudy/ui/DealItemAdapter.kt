package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.models.DealItem
import kotlinx.android.synthetic.main.deal_list_item.view.*

class DealItemAdapter(private val deals: List<DealItem>) :
    RecyclerView.Adapter<DealItemAdapter.DealItemViewHolder>() {

    var onItemClick: ((DealItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view).apply {
            itemClick = {
                this@DealItemAdapter.onItemClick?.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    override fun onBindViewHolder(holder: DealItemViewHolder, position: Int) {
        holder.bindItems(deals[position])
        //handling the flag requirement.
        Glide.with(holder.itemView.context)
            .load(deals[position].imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.deal_list_item_image_view)
    }

    class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemClick: ((DealItem) -> Unit)? = null
        fun bindItems(deal: DealItem) {
            val dealTitle = itemView.findViewById(R.id.deal_list_item_title) as TextView
            val dealPrice = itemView.findViewById(R.id.deal_list_item_price) as TextView
            val dealAisle = itemView.findViewById(R.id.deal_list_item_aisle) as TextView
            dealTitle.text = deal.title
            dealPrice.text = deal.price?.displayValue
            dealAisle.text = deal.aisle
            itemView.setOnClickListener {
                itemClick?.invoke(deal)

            }
        }

    }
}
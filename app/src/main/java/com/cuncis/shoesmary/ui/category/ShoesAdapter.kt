package com.cuncis.shoesmary.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Product
import kotlinx.android.synthetic.main.item_discount.view.*
import kotlinx.android.synthetic.main.item_sort_vertical.view.*
import kotlinx.android.synthetic.main.item_sort_vertical.view.tv_diskon

class ShoesAdapter(private var shoesList: ArrayList<Product>, private val itemAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<ShoesAdapter.ShoesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sort_vertical, parent, false)
        return ShoesViewHolder(view)
    }

    override fun getItemCount(): Int = shoesList.size

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        holder.tvDiskon.text = shoesList[position].diskon
        holder.itemView.setOnClickListener {
            itemAdapterCallback.onClick(it, position)
        }
    }

    inner class ShoesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvDiskon: TextView = view.tv_diskon
    }

    interface ItemAdapterCallback {
        fun onClick(view: View, position: Int)
    }
}
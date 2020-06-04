package com.cuncis.shoesmary.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Product
import kotlinx.android.synthetic.main.item_discount.view.*

class HomeAdapter(private var productList: ArrayList<Product>, private val itemAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.tvDiscount.text = productList[position].diskon
        holder.imgPoster.setOnClickListener {
            itemAdapterCallback.onClick(it, position)
        }
    }

    inner class HomeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.iv_poster
        val tvDiscount: TextView = view.tv_diskon
    }

    interface ItemAdapterCallback {
        fun onClick(view: View, position: Int)
    }
}
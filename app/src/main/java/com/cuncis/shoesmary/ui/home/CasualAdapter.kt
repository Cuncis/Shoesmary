package com.cuncis.shoesmary.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Product
import com.cuncis.shoesmary.model.product.CasualItem
import com.cuncis.shoesmary.util.Utils.Companion.setImageFromUrl
import kotlinx.android.synthetic.main.item_discount.view.*

class CasualAdapter(private var casualList: ArrayList<CasualItem>, private val itemAdapterCallback: ItemAdapterCallback)
    : RecyclerView.Adapter<CasualAdapter.CasualViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasualViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false)
        return CasualViewHolder(view)
    }

    override fun getItemCount(): Int = casualList.size

    override fun onBindViewHolder(holder: CasualViewHolder, position: Int) {
        holder.tvDiscount.text = casualList[position].disc
        holder.imgPoster.setImageFromUrl(casualList[position].poster)
        holder.imgPoster.setOnClickListener {
            itemAdapterCallback.onCasualClick(it, casualList[position])
        }
    }

    inner class CasualViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.iv_poster
        val tvDiscount: TextView = view.tv_diskon
    }

    interface ItemAdapterCallback {
        fun onCasualClick(view: View, data: CasualItem)
    }
}
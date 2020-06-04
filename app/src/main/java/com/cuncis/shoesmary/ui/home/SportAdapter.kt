package com.cuncis.shoesmary.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.product.SportItem
import com.cuncis.shoesmary.util.Utils.Companion.setImageFromUrl
import kotlinx.android.synthetic.main.item_discount.view.*

class SportAdapter(private var sportList: ArrayList<SportItem>, private val itemAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<SportAdapter.SportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false)
        return SportViewHolder(view)
    }

    override fun getItemCount(): Int = sportList.size

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.tvDiscount.text = sportList[position].disc
        holder.imgPoster.setImageFromUrl(sportList[position].poster)
        holder.imgPoster.setOnClickListener {
            itemAdapterCallback.onSportClick(it, sportList[position])
        }
    }

    inner class SportViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.iv_poster
        val tvDiscount: TextView = view.tv_diskon
    }

    interface ItemAdapterCallback {
        fun onSportClick(view: View, data: SportItem)
    }
}
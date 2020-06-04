package com.cuncis.shoesmary.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Color
import com.cuncis.shoesmary.model.Size
import kotlinx.android.synthetic.main.item_colors.view.*
import kotlinx.android.synthetic.main.item_size.view.*

class SizeAdapter(private var sizeList: ArrayList<Size>, private val itemAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_size, parent, false)
        return SizeViewHolder(view)
    }

    override fun getItemCount(): Int = sizeList.size

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.tvSize.text = sizeList[position].label
        holder.tvSize.setOnClickListener {
            itemAdapterCallback.onClick(it, position)
        }
    }

    inner class SizeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvSize: TextView = view.tv_size
    }

    interface ItemAdapterCallback {
        fun onClick(view: View, position: Int)
    }
}
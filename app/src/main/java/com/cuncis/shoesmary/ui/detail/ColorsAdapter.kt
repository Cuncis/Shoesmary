package com.cuncis.shoesmary.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Color
import kotlinx.android.synthetic.main.item_colors.view.*

class ColorsAdapter(private var colorList: ArrayList<Color>, private val itemAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_colors, parent, false)
        return ColorViewHolder(view)
    }

    override fun getItemCount(): Int = colorList.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.imgColor.setImageResource(colorList[position].colors)
        holder.imgColor.setOnClickListener {
            itemAdapterCallback.onClick(it, position)
        }
    }

    inner class ColorViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgColor: ImageView = view.iv_colors
    }

    interface ItemAdapterCallback {
        fun onClick(view: View, position: Int)
    }
}
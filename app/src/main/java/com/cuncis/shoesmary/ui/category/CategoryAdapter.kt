package com.cuncis.shoesmary.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private var categoryList: ArrayList<Category>, private val itemAdapterCallback: ItemAdapterCallback) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.tvLabel.text = categoryList[position].label
        holder.tvLabel.setOnClickListener {
            itemAdapterCallback.onClick(it, position)
        }
    }

    inner class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvLabel: TextView = view.tv_label
    }

    interface ItemAdapterCallback {
        fun onClick(view: View, position: Int)
    }
}
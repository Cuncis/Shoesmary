package com.cuncis.shoesmary.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class Utils {
    companion object {
        fun ImageView.setImageFromUrl(url: String) {
            Glide.with(this)
                .load(url)
                .into(this)
        }
        
        fun convertPrice(price: Int): String {
            val locale = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(locale)

            return numberFormat.format(price)
        }

        fun convertPrice(price: String): String {
            val locale = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(locale)

            return numberFormat.format(price.toDouble())
        }

        fun convertPrice(price: Double): String {
            val locale = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(locale)

            return numberFormat.format(price)
        }
        fun View.show() {
            this.visibility = View.VISIBLE
        }

        fun View.hide() {
            this.visibility = View.GONE
        }
    }
}
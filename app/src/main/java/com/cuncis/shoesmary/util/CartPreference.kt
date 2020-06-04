package com.cuncis.shoesmary.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.cuncis.shoesmary.model.CartData
import com.cuncis.shoesmary.util.Constants.KEY_DESC
import com.cuncis.shoesmary.util.Constants.KEY_DISKON
import com.cuncis.shoesmary.util.Constants.KEY_ID
import com.cuncis.shoesmary.util.Constants.KEY_POSTER
import com.cuncis.shoesmary.util.Constants.KEY_PRICE
import com.cuncis.shoesmary.util.Constants.KEY_PRICE_DISKON
import com.cuncis.shoesmary.util.Constants.KEY_TITLE
import com.cuncis.shoesmary.util.Constants.PREF_NAME

class CartPreference {
    companion object {
        private fun getSharedPreferece(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        }

        fun setCartData(context: Context, id: String, title: String, poster: String,
                        discount: String, desc: String, price: String, priceDisc: String) {
            val editor = getSharedPreferece(context).edit()
            editor.putString(KEY_ID, id)
            editor.putString(KEY_TITLE, title)
            editor.putString(KEY_POSTER, poster)
            editor.putString(KEY_DISKON, discount)
            editor.putString(KEY_DESC, desc)
            editor.putString(KEY_PRICE, price)
            editor.putString(KEY_PRICE_DISKON, priceDisc)
            editor.apply()
        }

        fun getCartData(context: Context): CartData {
            return CartData(
                getSharedPreferece(context).getString(KEY_ID, "").toString(),
                getSharedPreferece(context).getString(KEY_TITLE, "").toString(),
                getSharedPreferece(context).getString(KEY_POSTER, "").toString(),
                getSharedPreferece(context).getString(KEY_DISKON, "").toString(),
                getSharedPreferece(context).getString(KEY_DESC, "").toString(),
                getSharedPreferece(context).getString(KEY_PRICE, "").toString(),
                getSharedPreferece(context).getString(KEY_PRICE_DISKON, "").toString()
            )
        }

        fun getCartId(context: Context): String {
            return getSharedPreferece(context).getString(KEY_ID, "").toString()
        }

        fun clearData(context: Context) {
            val editor = getSharedPreferece(context).edit()
            editor.clear()
            editor.apply()
        }
    }
}
package com.cuncis.shoesmary.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.ui.cart.CartActivity
import com.cuncis.shoesmary.ui.category.CategoryActivity
import com.cuncis.shoesmary.util.Constants
import com.cuncis.shoesmary.viewmodel.ShippingViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_toolbar_home.view.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        layout_toolbar.apply {
            menu_cart.setOnClickListener {
                val intent = Intent(this@HomeActivity, CartActivity::class.java)
                startActivity(intent)
            }
            menu_filter.setOnClickListener {
                val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
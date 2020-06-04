package com.cuncis.shoesmary.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.cuncis.shoesmary.R
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        navController = NavHostFragment.findNavController(nav_host_fragment)

    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.paymentFragment -> {
                Toast.makeText(this, "Back From Payment", Toast.LENGTH_SHORT).show()
                navController.navigateUp()
            }
            R.id.shippingFragment -> {
                Toast.makeText(this, "Back From Shipping", Toast.LENGTH_SHORT).show()
                navController.navigateUp()
            }
            else -> {
                Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show()
                super.onBackPressed()
            }
        }
    }
}
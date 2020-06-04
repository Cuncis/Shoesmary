package com.cuncis.shoesmary.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.util.CartPreference
import com.cuncis.shoesmary.util.Utils
import com.cuncis.shoesmary.util.Utils.Companion.hide
import com.cuncis.shoesmary.util.Utils.Companion.setImageFromUrl
import com.cuncis.shoesmary.util.Utils.Companion.show
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment(R.layout.fragment_cart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (CartPreference.getCartId(requireContext()).isEmpty()) {
            ll_empty.show()
            rl_cart_detail.hide()
            ll_cart_checkout.hide()
        } else {
            val cartData = CartPreference.getCartData(requireContext())
            tv_title.text = cartData.title
            tv_desc.text = cartData.description
            tv_price.text = Utils.convertPrice(cartData.price)
            tv_priceTotal.text = Utils.convertPrice(cartData.price)
            iv_produk.setImageFromUrl(cartData.poster)
        }

        view.apply {
            btn_checkout.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_shippingFragment)
            }
        }
    }
}
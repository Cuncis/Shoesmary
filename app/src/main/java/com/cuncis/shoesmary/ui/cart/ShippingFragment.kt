package com.cuncis.shoesmary.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.UserDetails
import com.cuncis.shoesmary.util.CartPreference
import com.cuncis.shoesmary.util.Constants.ONGKIR_EXTRA
import com.cuncis.shoesmary.util.Constants.TAG
import com.cuncis.shoesmary.util.Constants.USER_DETAIL_EXTRA
import com.cuncis.shoesmary.util.Utils
import com.cuncis.shoesmary.util.Utils.Companion.setImageFromUrl
import com.cuncis.shoesmary.util.setOnSingleClickListener
import com.cuncis.shoesmary.viewmodel.ShippingViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.fragment_shipping.*
import kotlinx.android.synthetic.main.fragment_shipping.btnPayment
import kotlinx.android.synthetic.main.fragment_shipping.view.*
import kotlinx.android.synthetic.main.layout_shipping_form.*
import kotlinx.android.synthetic.main.layout_shipping_item.*


class ShippingFragment : Fragment(R.layout.fragment_shipping) {

    private lateinit var shippingViewModel: ShippingViewModel
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shippingViewModel = ViewModelProvider(requireActivity()).get(ShippingViewModel::class.java)
        navController = Navigation.findNavController(view)

        val viewDetail = view.idShippingDetail
        val viewForm = view.idShippingForm

        observeViewModel()

        viewDetail.apply {
            val cartData = CartPreference.getCartData(requireContext())
            iv_produk.setImageFromUrl(cartData.poster)
            tv_title.text = cartData.title
            tv_desc.text = cartData.description
            tv_amount_value.text = Utils.convertPrice(cartData.price)
        }

        viewForm.apply {
            et_firstname.setText("Karang")
            et_lastName.setText("Reksa")
            et_email.setText("karang.reksa@gmail.com")
            et_phone.setText("083854775376")

            et_addressLine.setText("Pondok Benowo Indah")
            et_city.setText("Surabaya")
            et_postCode.setText("60197")
            et_country.setText("IDN")
        }

        view.apply {
            btnPayment.setOnSingleClickListener {
                val userDetails = UserDetails(
                    "${et_firstname.text} ${et_lastName.text}",
                    et_email.text.toString(),
                    et_phone.text.toString(),
                    et_addressLine.text.toString(),
                    et_city.text.toString(),
                    et_postCode.text.toString(),
                    et_country.text.toString()
                )

                shippingViewModel.getCost("62", "62", "1400", "jne").observe(requireActivity(), Observer { response ->
                    if (response != null) {
                        val bundle = Bundle()
                        bundle.putParcelable(USER_DETAIL_EXTRA, userDetails)
                        bundle.putParcelable(ONGKIR_EXTRA, response.rajaongkir)
                        when (navController.currentDestination?.id) {
                            R.id.shippingFragment -> {
                                findNavController().navigate(R.id.action_shippingFragment_to_paymentFragment, bundle)
                            }
                        }
                    } else {
                        Log.d(TAG, "onViewCreated: Cost Response: $response")
                    }
                })
            }
        }
    }

    private fun observeViewModel() {
        shippingViewModel.message.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(), " $it", Toast.LENGTH_SHORT).show()
        })
        shippingViewModel.loading.observe(requireActivity(), Observer { loading ->
            if (loading) {
                Log.d(TAG, "observeViewModel: Loading VISIBLE")
            } else {
                Log.d(TAG, "observeViewModel: Loading GONE")
            }
        })
    }

}
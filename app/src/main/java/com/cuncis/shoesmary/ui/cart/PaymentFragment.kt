package com.cuncis.shoesmary.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.cuncis.shoesmary.BuildConfig
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.CustomerModel
import com.cuncis.shoesmary.model.UserDetails
import com.cuncis.shoesmary.model.cost.Rajaongkir
import com.cuncis.shoesmary.util.CartPreference
import com.cuncis.shoesmary.util.Constants
import com.cuncis.shoesmary.util.Constants.TAG
import com.cuncis.shoesmary.util.Utils
import com.cuncis.shoesmary.util.Utils.Companion.setImageFromUrl
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.PaymentMethod
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.snap.TransactionResult
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.fragment_payment.view.*
import kotlinx.android.synthetic.main.layout_shipping_item.*
import kotlinx.android.synthetic.main.layout_shipping_item.iv_produk
import kotlinx.android.synthetic.main.layout_shipping_item.tv_desc
import kotlinx.android.synthetic.main.layout_shipping_item.tv_title


class PaymentFragment : Fragment(R.layout.fragment_payment), TransactionFinishedCallback {

    private lateinit var tvAddress: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewDetail = view.includeDetail
        val viewPaymentDetail = view.includePaymentDetail

        tvAddress = view.includePaymentDetail.findViewById(R.id.tv_desc)

        val userDetails: UserDetails = arguments?.getParcelable(Constants.USER_DETAIL_EXTRA)!!
        val rajaOngkir: Rajaongkir = arguments?.getParcelable(Constants.ONGKIR_EXTRA)!!

        tvAddress.text = "${userDetails.name}, ${userDetails.address}, ${rajaOngkir.destinationDetails.cityName}"

        val data = CartPreference.getCartData(requireContext())
        viewPaymentDetail.apply {
            iv_produk.setImageFromUrl(data.poster)
            tv_title.text = data.title
            tv_desc.text = data.description
            tv_amount_value.text = Utils.convertPrice(data.price)
        }

        initMidtrans()
        CustomerModel.userDetails(
            userDetails.name,
            userDetails.email,
            userDetails.phone,
            userDetails.address,
            userDetails.city,
            userDetails.posCode,
            userDetails.country
        )


        view.apply {
            btnPayNow.setOnClickListener {
                MidtransSDK.getInstance().transactionRequest = CustomerModel.transactionRequest(
                    data.id,
                    data.price.toDouble(),
                    1,
                    data.title
                )
                MidtransSDK.getInstance().startPaymentUiFlow(activity, PaymentMethod.BANK_TRANSFER_MANDIRI)
            }
        }

    }

    private fun initMidtrans() {
        SdkUIFlowBuilder.init()
            .setClientKey(BuildConfig.CLIENT_KEY) // client_key is mandatory
            .setContext(activity) // context is mandatory
            .setTransactionFinishedCallback(this) // set transaction finish callback (sdk callback)
            .setMerchantBaseUrl(BuildConfig.BASE_URL) //set merchant url (required)
            .enableLog(true) // enable sdk log (optional)
            .setColorTheme(
                CustomColorTheme(
                    "#FFE51255",
                    "#B61548",
                    "#FFE51255"
                )
            ) // set theme. it will replace theme on snap theme on MAP ( optional)
            .buildSDK()
    }

    override fun onTransactionFinished(result: TransactionResult?) {
        if (result?.response != null) {
            when (result.status) {
                TransactionResult.STATUS_SUCCESS -> {
                    Toast.makeText(activity, "Transaksi Sukses dengan id ${result.response.transactionId}", Toast.LENGTH_SHORT)
                        .show()
                }
                TransactionResult.STATUS_PENDING -> {
                    Toast.makeText(activity, "Transaksi Pending dengan id ${result.response.transactionId}", Toast.LENGTH_SHORT)
                        .show()
                }
                TransactionResult.STATUS_FAILED -> {
                    Toast.makeText(activity, "Transaksi Gagal dengan id ${result.response.transactionId}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            result.response.validationMessages
        } else if (result?.isTransactionCanceled!!) {
            Toast.makeText(activity, "Transaksi dibatalkan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Transaksi Finish with Fail", Toast.LENGTH_SHORT).show()
        }
    }

}
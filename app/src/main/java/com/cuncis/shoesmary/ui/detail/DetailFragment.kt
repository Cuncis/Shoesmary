package com.cuncis.shoesmary.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Color
import com.cuncis.shoesmary.model.Size
import com.cuncis.shoesmary.model.product.CasualItem
import com.cuncis.shoesmary.model.product.SportItem
import com.cuncis.shoesmary.ui.cart.CartActivity
import com.cuncis.shoesmary.util.CartPreference
import com.cuncis.shoesmary.util.Constants
import com.cuncis.shoesmary.util.Constants.PRODUCT_DETAIL_EXTRA
import com.cuncis.shoesmary.util.Constants.STATUS_DETAIL_EXTRA
import com.cuncis.shoesmary.util.Utils
import com.cuncis.shoesmary.util.Utils.Companion.setImageFromUrl
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.rv_color
import kotlinx.android.synthetic.main.fragment_detail.rv_size
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_colors.view.*
import kotlinx.android.synthetic.main.item_size.view.*


class DetailFragment : Fragment(R.layout.fragment_detail), ColorsAdapter.ItemAdapterCallback, SizeAdapter.ItemAdapterCallback {

    private var colorList = ArrayList<Color>()
    private var sizeList = ArrayList<Size>()
    private lateinit var colorAdapter: ColorsAdapter
    private lateinit var sizeAdapter: SizeAdapter

    private var status: String? = null
    private var sportData: SportItem? = null
    private var casualData: CasualItem? = null

    private lateinit var tvTitle: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvDiskon: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvPriceDiskon: TextView
    private lateinit var ivPoster: ImageView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorAdapter = ColorsAdapter(colorList, this)
        sizeAdapter = SizeAdapter(sizeList, this)

        ivPoster = view.iv_produk
        tvTitle = view.textView4
        tvDesc = view.textView11
        tvDiskon = view.textView7
        tvPrice = view.textView9
        tvPriceDiskon = view.textView8

        colorList.add(Color(1, R.color.colorAccent))
        colorList.add(Color(2, R.color.colorGrey))
        colorList.add(Color(3, R.color.colorPrimary))

        sizeList.add(Size(1, "36\nEU"))
        sizeList.add(Size(2, "36\nEU"))
        sizeList.add(Size(3, "36\nEU"))
        sizeList.add(Size(4, "36\nEU"))
        sizeList.add(Size(5, "36\nEU"))
        sizeList.add(Size(6, "36\nEU"))

        status = arguments?.getString(STATUS_DETAIL_EXTRA)
        if (status?.equals(Constants.SPORT_STATUS, true)!!) {
            sportData = arguments?.getParcelable(PRODUCT_DETAIL_EXTRA)
            if (sportData != null) {
                setSportData(sportData!!)
            }
        } else {
            casualData = arguments?.getParcelable(PRODUCT_DETAIL_EXTRA)
            if (casualData != null) {
                setCasualData(casualData!!)
            }
        }


        view.apply {
            rv_color.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = colorAdapter
                colorAdapter.notifyDataSetChanged()
            }
            rv_size.apply {
                layoutManager = GridLayoutManager(activity, 4)
                adapter = sizeAdapter
                sizeAdapter.notifyDataSetChanged()
            }
            btn_cart.setOnClickListener {
                val intent = Intent(activity, CartActivity::class.java)
                if (status?.equals(Constants.SPORT_STATUS, true)!!) {
                    if (sportData != null) {
                        CartPreference.setCartData(requireContext(), sportData?.code!!, sportData?.title!!,
                            sportData?.poster!!, sportData?.disc!!, sportData?.desc!!, sportData?.priceReal!!,
                            sportData?.pricePromo!!)
                    }
                } else {
                    if (casualData != null) {
                        CartPreference.setCartData(requireContext(), casualData?.code!!, casualData?.title!!,
                            casualData?.poster!!, casualData?.disc!!, casualData?.desc!!, casualData?.priceReal!!,
                            casualData?.pricePromo!!)
                    }
                }
                startActivity(intent)
            }
        }
    }

    private fun setCasualData(casualData: CasualItem) {
        ivPoster.setImageFromUrl(casualData.poster)
        tvTitle.text = casualData.title
        tvDesc.text = casualData.desc
        tvDiskon.text = casualData.disc
        tvPrice.text = Utils.convertPrice(casualData.price)
        tvPriceDiskon.text = Utils.convertPrice(casualData.pricePromo)
    }

    private fun setSportData(sportData: SportItem) {
        ivPoster.setImageFromUrl(sportData.poster)
        tvTitle.text = sportData.title
        tvDesc.text = sportData.desc
        tvDiskon.text = sportData.disc
        tvPrice.text = Utils.convertPrice(sportData.price)
        tvPriceDiskon.text = Utils.convertPrice(sportData.pricePromo)
    }

    override fun onClick(view: View, position: Int) {
        if (view.id == R.id.iv_colors) {
            Toast.makeText(activity, "Color $position", Toast.LENGTH_SHORT).show()
        } else if (view.id == R.id.tv_size) {
            Toast.makeText(activity, "Size $position", Toast.LENGTH_SHORT).show()
        }
    }

}
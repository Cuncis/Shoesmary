package com.cuncis.shoesmary.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.product.CasualItem
import com.cuncis.shoesmary.model.product.SportItem
import com.cuncis.shoesmary.ui.category.CategoryActivity
import com.cuncis.shoesmary.util.Constants
import com.cuncis.shoesmary.util.Constants.CASUAL_STATUS
import com.cuncis.shoesmary.util.Constants.SPORT_STATUS
import com.cuncis.shoesmary.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.layout_header.view.*
import kotlinx.android.synthetic.main.layout_header_more.view.*

class HomeFragment : Fragment(R.layout.fragment_home), SportAdapter.ItemAdapterCallback, CasualAdapter.ItemAdapterCallback {

    private var sportList = ArrayList<SportItem>()
    private var casualList = ArrayList<CasualItem>()

    private lateinit var tvActionSport: TextView
    private lateinit var tvActionCasual: TextView
    private lateinit var tvHeaderCasual: TextView
    private lateinit var tvShopNow: TextView
    private lateinit var ivShopNow: ImageView

    private lateinit var sportAdapter: SportAdapter
    private lateinit var casualAdapter: CasualAdapter
    private lateinit var productViewModel: ProductsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(requireActivity()).get(ProductsViewModel::class.java)

        sportAdapter = SportAdapter(sportList, this)
        casualAdapter = CasualAdapter(casualList, this)

        initView(view)

        initRecyclerView(view)

        initListener()

        observeViewModel()
    }

    private fun observeViewModel() {
        productViewModel.getProducts().observe(requireActivity(), Observer { data ->
            setSportData(data.sport)
            setCasualData(data.casual)
        })
    }

    private fun setCasualData(casual: List<CasualItem>) {
        casualList.clear()
        casualList.addAll(casual)
        casualAdapter.notifyDataSetChanged()
    }

    private fun setSportData(sport: List<SportItem>) {
        sportList.clear()
        sportList.addAll(sport)
        sportAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(view: View) {
        view.apply {
            rv_sport.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                isNestedScrollingEnabled = false
                adapter = sportAdapter
            }
            rv_casual.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                isNestedScrollingEnabled = false
                adapter = casualAdapter
            }
        }
    }

    private fun initView(view: View) {
        val header = view.layout_header
        val headerSport = view.layout_header_sport
        val headerCasual = view.layout_header_casual

        tvActionSport = headerSport.tv_action
        tvActionCasual = headerCasual.tv_action
        ivShopNow = header.iv_shop_now
        tvShopNow = header.tv_shop_now
        tvHeaderCasual = headerCasual.tv_title

        tvHeaderCasual.text = "Casual Shoes"
    }

    private fun initListener() {
        ivShopNow.setOnClickListener {
            Toast.makeText(activity, "Shop Now Image", Toast.LENGTH_SHORT).show()
            val goToCategory = Intent(activity, CategoryActivity::class.java)
            startActivity(goToCategory)
        }
        tvShopNow.setOnClickListener {
            Toast.makeText(activity, "Shop Now Text", Toast.LENGTH_SHORT).show()
            val goToCategory = Intent(activity, CategoryActivity::class.java)
            startActivity(goToCategory)
        }
    }

    override fun onSportClick(view: View, data: SportItem) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.PRODUCT_DETAIL_EXTRA, data)
        bundle.putString(Constants.STATUS_DETAIL_EXTRA, SPORT_STATUS)
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    override fun onCasualClick(view: View, data: CasualItem) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.PRODUCT_DETAIL_EXTRA, data)
        bundle.putString(Constants.STATUS_DETAIL_EXTRA, CASUAL_STATUS)
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

}
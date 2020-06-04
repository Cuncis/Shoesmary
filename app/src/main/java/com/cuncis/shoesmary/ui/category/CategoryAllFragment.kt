package com.cuncis.shoesmary.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncis.shoesmary.R
import com.cuncis.shoesmary.model.Category
import com.cuncis.shoesmary.model.Product
import kotlinx.android.synthetic.main.fragment_category_all.*
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAllFragment : Fragment(R.layout.fragment_category_all), CategoryAdapter.ItemAdapterCallback, ShoesAdapter.ItemAdapterCallback {

    private var categoryList = ArrayList<Category>()
    private var shoesList = ArrayList<Product>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var shoesAdapter: ShoesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryList.clear()
        categoryAdapter = CategoryAdapter(categoryList, this)
        categoryList.add(Category(1, "All"))
        categoryList.add(Category(2, "Sport"))
        categoryList.add(Category(3, "Casual"))

        shoesList.clear()
        shoesAdapter = ShoesAdapter(shoesList, this)
        shoesList.add(Product(1, "Ariel", "New", "30%", "A"))
        shoesList.add(Product(2, "Beta", "New", "30%", "A"))
        shoesList.add(Product(3, "Cancel", "New", "30%", "A"))
        shoesList.add(Product(4, "Down", "New", "30%", "A"))
        shoesList.add(Product(5, "Elf", "New", "30%", "A"))
        view.apply {
            rv_categori.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = categoryAdapter
                categoryAdapter.notifyDataSetChanged()
            }
            rv_shoes.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = shoesAdapter
                shoesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(view: View, position: Int) {
        if (view.id == R.id.tv_label) {
            Toast.makeText(activity, "Click ${categoryList[position].label}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Click ${shoesList[position].nama}", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_categoryFragment_to_detailFragment2)
        }
    }
}
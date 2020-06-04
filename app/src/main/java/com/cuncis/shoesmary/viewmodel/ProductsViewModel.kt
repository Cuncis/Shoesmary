package com.cuncis.shoesmary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuncis.shoesmary.model.product.ProductData
import com.cuncis.shoesmary.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProductsViewModel : ViewModel() {

    private val repository = ProductsRepository()
    private var productList = MutableLiveData<ProductData>()
    var message = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()

    fun getProducts(): MutableLiveData<ProductData> {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getProducts()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        productList.postValue(response.body()?.data)
                    } else {
                        message.postValue(response.message())
                    }
                    loading.postValue(false)
                } catch (e: Exception) {
                    message.postValue(e.localizedMessage)
                    loading.postValue(false)
                }
            }
        }

        return productList
    }

}
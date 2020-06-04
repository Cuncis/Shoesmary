package com.cuncis.shoesmary.repository

import com.cuncis.shoesmary.data.ApiClient

class ProductsRepository {

    suspend fun getProducts()
            = ApiClient.theShoesmaryApiShop.getProducts("ecom")

}
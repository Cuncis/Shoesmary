package com.cuncis.shoesmary.repository

import com.cuncis.shoesmary.data.ApiClient

class ShippingRepository {

    suspend fun getCost(origin: String, destination: String, weight: String, courier: String)
            = ApiClient.theShoesmaryApi.getCost(origin, destination, weight, courier)

}
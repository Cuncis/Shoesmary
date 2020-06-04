package com.cuncis.shoesmary.data

import com.cuncis.shoesmary.BuildConfig
import com.cuncis.shoesmary.model.cost.CostResponse
import com.cuncis.shoesmary.model.product.ProductResponse
import retrofit2.Response
import retrofit2.http.*

interface TheShoesmaryApi {

    @FormUrlEncoded
    @POST("cost")
    suspend fun getCost(
        @Field("origin") origin: String,
        @Field("destination") destination: String,
        @Field("weight") weight: String,
        @Field("courier") courier: String,
        @Field("key") token: String = BuildConfig.RAJA_KEY
    ): Response<CostResponse>

    @GET("shop.php")
    suspend fun getProducts(@Query("code_apps") codeApps: String)
            : Response<ProductResponse>

}
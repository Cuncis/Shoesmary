package com.cuncis.shoesmary.model.city

import com.google.gson.annotations.SerializedName

data class CityResponse(

	@SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir
)

data class Query(

	@SerializedName("province")
	val province: String
)

data class Rajaongkir(

	@SerializedName("query")
	val query: Query,

	@SerializedName("results")
	val results: List<City>,

	@SerializedName("status")
	val status: Status
)

data class City(

	@SerializedName("city_name")
	val cityName: String,

	@SerializedName("province")
	val province: String,

	@SerializedName("province_id")
	val provinceId: String,

	@SerializedName("type")
	val type: String,

	@SerializedName("postal_code")
	val postalCode: String,

	@SerializedName("city_id")
	val cityId: String
)

data class Status(

	@SerializedName("code")
	val code: Int,

	@SerializedName("description")
	val description: String
)

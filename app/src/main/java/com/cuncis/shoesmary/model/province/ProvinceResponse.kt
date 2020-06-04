package com.cuncis.shoesmary.model.province

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(

	@SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir
)

data class Status(

	@SerializedName("code")
	val code: Int,

	@SerializedName("description")
	val description: String
)

data class Province(

	@SerializedName("province")
	val province: String,

	@SerializedName("province_id")
	val provinceId: String
)

data class Rajaongkir(

	@SerializedName("query")
	val query: List<Any>,

	@SerializedName("results")
	val results: List<Province>,

	@SerializedName("status")
	val status: Status
)

package com.cuncis.shoesmary.model.cost

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CostResponse(

	@SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir
): Parcelable

@Parcelize
data class OriginDetails(

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
): Parcelable

@Parcelize
data class Result(

	@SerializedName("costs")
	val costs: List<CostsItem>,

	@SerializedName("code")
	val code: String,

	@SerializedName("name")
	val name: String
): Parcelable

@Parcelize
data class Rajaongkir(

	@SerializedName("query")
	val query: Query,

	@SerializedName("destination_details")
	val destinationDetails: DestinationDetails,

	@SerializedName("origin_details")
	val originDetails: OriginDetails,

	@SerializedName("results")
	val results: List<Result>,

	@SerializedName("status")
	val status: Status
): Parcelable

@Parcelize
data class DestinationDetails(

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
): Parcelable

@Parcelize
data class CostItem(

	@SerializedName("note")
	val note: String,

	@SerializedName("etd")
	val etd: String,

	@SerializedName("value")
	val value: Int
): Parcelable

@Parcelize
data class Query(

	@SerializedName("courier")
	val courier: String,

	@SerializedName("origin")
	val origin: String,

	@SerializedName("destination")
	val destination: String,

	@SerializedName("weight")
	val weight: Int
): Parcelable

@Parcelize
data class Status(

	@SerializedName("code")
	val code: Int,

	@SerializedName("description")
	val description: String
): Parcelable

@Parcelize
data class CostsItem(

	@SerializedName("cost")
	val cost: List<CostItem>,

	@SerializedName("service")
	val service: String,

	@SerializedName("description")
	val description: String
): Parcelable

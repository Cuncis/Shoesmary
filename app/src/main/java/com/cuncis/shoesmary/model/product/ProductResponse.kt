package com.cuncis.shoesmary.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductResponse(

	@SerializedName("codeMessage")
	val codeMessage: String,

	@SerializedName("data")
	val data: ProductData,

	@SerializedName("codeStatus")
	val codeStatus: String
) : Parcelable

@Parcelize
data class ProductData(

	@SerializedName("casual")
	val casual: List<CasualItem>,

	@SerializedName("sport")
	val sport: List<SportItem>
) : Parcelable

@Parcelize
data class CasualItem(

	@SerializedName("code")
	val code: String,

	@SerializedName("price_promo")
	val pricePromo: String,

	@SerializedName("price")
	val price: String,

	@SerializedName("disc")
	val disc: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("price_real")
	val priceReal: String,

	@SerializedName("poster")
	val poster: String,

	@SerializedName("desc")
	val desc: String
) : Parcelable

@Parcelize
data class SportItem(

	@SerializedName("code")
	val code: String,

	@SerializedName("price_promo")
	val pricePromo: String,

	@SerializedName("price")
	val price: String,

	@SerializedName("disc")
	val disc: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("price_real")
	val priceReal: String,

	@SerializedName("poster")
	val poster: String,

	@SerializedName("desc")
	val desc: String
) : Parcelable

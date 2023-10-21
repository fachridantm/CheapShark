package com.technical.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DealsResponseItem(

	@field:SerializedName("gameID")
	val gameID: String,

	@field:SerializedName("metacriticScore")
	val metacriticScore: String? = null,

	@field:SerializedName("salePrice")
	val salePrice: String? = null,

	@field:SerializedName("releaseDate")
	val releaseDate: Int? = null,

	@field:SerializedName("thumb")
	val thumb: String,

	@field:SerializedName("dealID")
	val dealID: String,

	@field:SerializedName("steamRatingCount")
	val steamRatingCount: String? = null,

	@field:SerializedName("metacriticLink")
	val metacriticLink: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("storeID")
	val storeID: String,

	@field:SerializedName("steamAppID")
	val steamAppID: String? = null,

	@field:SerializedName("internalName")
	val internalName: String? = null,

	@field:SerializedName("steamRatingPercent")
	val steamRatingPercent: String? = null,

	@field:SerializedName("dealRating")
	val dealRating: String? = null,

	@field:SerializedName("normalPrice")
	val normalPrice: String? = null,

	@field:SerializedName("lastChange")
	val lastChange: Int? = null,

	@field:SerializedName("savings")
	val savings: String? = null,

	@field:SerializedName("isOnSale")
	val isOnSale: String? = null,

	@field:SerializedName("steamRatingText")
	val steamRatingText: String? = null
)
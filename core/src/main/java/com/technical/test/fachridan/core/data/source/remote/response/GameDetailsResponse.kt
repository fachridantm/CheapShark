package com.technical.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailsResponse(

	@field:SerializedName("cheapestPriceEver")
	val cheapestPriceEver: CheapestPriceEver,

	@field:SerializedName("deals")
	val deals: List<DealsItem>,

	@field:SerializedName("info")
	val info: Info? = null
)
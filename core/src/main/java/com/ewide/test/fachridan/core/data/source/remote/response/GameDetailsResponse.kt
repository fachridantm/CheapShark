package com.ewide.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailsResponse(

	@field:SerializedName("cheapestPriceEver")
	val cheapestPriceEver: CheapestPriceEver? = null,

	@field:SerializedName("deals")
	val deals: List<DealsItem>? = null,

	@field:SerializedName("info")
	val info: Info? = null
)
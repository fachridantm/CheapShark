package com.technical.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CheapestPriceEver(

	@field:SerializedName("date")
	val date: Long,

	@field:SerializedName("price")
	val price: String? = null
)
package com.ewide.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CheapestPriceEver(

	@field:SerializedName("date")
	val date: Long? = null,

	@field:SerializedName("price")
	val price: String? = null
)
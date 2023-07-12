package com.ewide.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StoresResponse(

	@field:SerializedName("StoresResponse")
	val storesResponse: List<StoresResponseItem?>? = null
)
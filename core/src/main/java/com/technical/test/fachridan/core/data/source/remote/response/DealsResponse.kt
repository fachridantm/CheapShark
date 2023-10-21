package com.technical.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DealsResponse(

	@field:SerializedName("DealsResponse")
	val dealsResponse: List<DealsResponseItem>
)
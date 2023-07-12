package com.ewide.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Info(

	@field:SerializedName("steamAppID")
	val steamAppID: String? = null,

	@field:SerializedName("thumb")
	val thumb: String,

	@field:SerializedName("title")
	val title: String? = null
)
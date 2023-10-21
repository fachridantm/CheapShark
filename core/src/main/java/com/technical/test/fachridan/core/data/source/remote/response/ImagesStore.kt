package com.technical.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ImagesStore(

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("banner")
	val banner: String,

	@field:SerializedName("logo")
	val logo: String
)
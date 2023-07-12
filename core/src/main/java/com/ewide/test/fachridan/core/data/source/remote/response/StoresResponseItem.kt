package com.ewide.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StoresResponseItem(

	@field:SerializedName("imagesStore")
	val imagesStore: ImagesStore? = null,

	@field:SerializedName("storeName")
	val storeName: String? = null,

	@field:SerializedName("storeID")
	val storeID: String,

	@field:SerializedName("isActive")
	val isActive: Int? = null
)
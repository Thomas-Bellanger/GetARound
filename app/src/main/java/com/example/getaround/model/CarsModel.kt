package com.example.getaround.model

import com.google.gson.annotations.SerializedName

data class CarsModel(

	@field:SerializedName("owner")
	val owner: Owner? = null,

	@field:SerializedName("picture_url")
	val pictureUrl: String? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null,

	@field:SerializedName("model")
	val model: String? = null,

	@field:SerializedName("price_per_day")
	val pricePerDay: Int? = null,

	@field:SerializedName("brand")
	val brand: String? = null
)

data class Owner(

	@field:SerializedName("picture_url")
	val pictureUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null
)

data class Rating(

	@field:SerializedName("average")
	val average: Double? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

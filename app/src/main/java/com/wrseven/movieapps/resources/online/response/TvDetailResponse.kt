package com.wrseven.movieapps.resources.online.response

import com.google.gson.annotations.SerializedName

data class TvDetailResponse(
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("first_air_date")
    val year: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("vote_average")
    val rating: Double
)
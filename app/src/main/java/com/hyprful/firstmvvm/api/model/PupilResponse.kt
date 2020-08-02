package com.hyprful.firstmvvm.api.model

import com.google.gson.annotations.SerializedName


data class PupilResponse(
    @SerializedName("pageNumber")
    var pageNumber: Int ,
    @SerializedName("items")
    var pupils : List<Pupil> ,
    @SerializedName("itemCount")
    var itemCount: Int,
    @SerializedName("totalPages")
    var totalPages: Int
)
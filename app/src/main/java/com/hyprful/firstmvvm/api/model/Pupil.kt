package com.hyprful.firstmvvm.api.model

import com.google.gson.annotations.SerializedName
import com.hyprful.firstmvvm.db.entity.PupilEntity


data class Pupil(
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("image")
    var image: String? = null ,
    @SerializedName("latitude")
    var latitude: Double?  = null,
    @SerializedName("longitude")
    var longitude: Double? = null ,
    @SerializedName("name")
    var name: String? = null ,
    @SerializedName("pupilId")
    var pupilId: Int?  = null
)  {
    constructor(pupilEntity: PupilEntity) : this() {
        country = pupilEntity.country
        name = pupilEntity.name
        latitude = pupilEntity.latitude
        longitude = pupilEntity.longitude
    }

}
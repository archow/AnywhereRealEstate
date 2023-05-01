package com.android.anywhererealestate.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiResponse(
    @SerializedName("AbstractUrl") val abstractUrl: String,
    @SerializedName("Heading") val heading: String,
    @SerializedName("RelatedTopics") val relatedTopics: List<RelatedTopic>
) : Parcelable

@Parcelize
data class RelatedTopic(
    @SerializedName("FirstURL") val firstURL: String,
    @SerializedName("Icon") val icon: Icon,
    @SerializedName("Result") val result: String,
    @SerializedName("Text") val text: String
): Parcelable

@Parcelize
data class Icon(
    @SerializedName("Height") val height: String,
    @SerializedName("URL") val url: String,
    @SerializedName("Width") val width: String
): Parcelable
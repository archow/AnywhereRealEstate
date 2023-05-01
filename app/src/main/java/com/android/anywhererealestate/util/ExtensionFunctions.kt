package com.android.anywhererealestate.util

import com.android.anywhererealestate.model.RelatedTopic

fun RelatedTopic.toName(): String {
    val urlName = this.firstURL.substringAfter("https://duckduckgo.com/")
        .split("_")
    return "${urlName[0]} ${urlName[1]}"
}
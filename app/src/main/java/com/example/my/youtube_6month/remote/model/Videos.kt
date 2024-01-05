package com.example.my.youtube_6month.remote.model

data class Videos(
    val items: List<Items>,
)

data class Items(
    val contentDetails: ContentDetails,
) {
    data class ContentDetails(
        val duration: String,
    )
}
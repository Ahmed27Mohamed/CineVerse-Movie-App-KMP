package com.a2004256_ahmedmohamed.movieapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    val results: List<VideoDto>
)
@Serializable
data class VideoDto(
    val key: String,
    val site: String,
    val type: String,
    val name: String
)
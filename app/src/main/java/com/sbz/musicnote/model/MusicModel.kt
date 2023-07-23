package com.sbz.musicnote.model

data class MusicModel(
    val id: Long,
    val title: String,
    val artist: String,
    val data: String,
    val albumArtUri: String
)

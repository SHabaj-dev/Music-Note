package com.sbz.musicnote.ui.favSongs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Fav Fragment"
    }
    val text: LiveData<String> = _text
}
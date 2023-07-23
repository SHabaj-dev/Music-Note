package com.sbz.musicnote.ui.sharedViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sbz.musicnote.model.MusicModel

class SharedViewModel : ViewModel() {

    private val _selectedSong = MutableLiveData<MusicModel>()
    val selectedSong: LiveData<MusicModel> get() = _selectedSong

    fun setSelectedSong(song: MusicModel) {
        _selectedSong.value = song
    }


}
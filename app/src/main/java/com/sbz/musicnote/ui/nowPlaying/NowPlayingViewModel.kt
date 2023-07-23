package com.sbz.musicnote.ui.nowPlaying

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sbz.musicnote.model.MusicModel

class NowPlayingViewModel : ViewModel() {

    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private val _selectedSong = MutableLiveData<MusicModel>()
    val selectedSong: LiveData<MusicModel> get() = _selectedSong
    var isPlaying = false

    fun setSelectedSong(song: MusicModel) {
        _selectedSong.value = song
    }


    fun playSong(song: MusicModel) {
        mediaPlayer.reset()
        try {
            mediaPlayer.setDataSource(song.data)
            mediaPlayer.prepare()
            mediaPlayer.start()
            isPlaying = true
            _selectedSong.value = song
        } catch (e: Exception) {
            Log.d("PLAYING_ERROR", "playSong: ${e.message}")
        }
    }

    fun pauseSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
        }
    }

    fun stopSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            isPlaying = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.release()
        isPlaying = false
    }

}
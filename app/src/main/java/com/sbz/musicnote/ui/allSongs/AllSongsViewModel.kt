package com.sbz.musicnote.ui.allSongs

import android.app.Application
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sbz.musicnote.model.MusicModel

class AllSongsViewModel(application: Application) : AndroidViewModel(application) {

    private val _songsLiveData = MutableLiveData<List<MusicModel>>()
    val songsLiveData: LiveData<List<MusicModel>> get() = _songsLiveData

    init {
        loadAllSongs()
    }

    private fun loadAllSongs() {
        val musicList = ArrayList<MusicModel>()

        val contentResolver = getApplication<Application>().contentResolver
        val musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID
        )

        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
        val cursor = contentResolver.query(musicUri, projection, null, null, sortOrder)

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            val albumIdColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val title = it.getString(titleColumn)
                val artist = it.getString(artistColumn)
                val data = it.getString(dataColumn)
                val albumId = it.getLong(albumIdColumn)

                val albumArtUri = getAlbumArtUri(albumId)

                musicList.add(MusicModel(id, title, artist, data, albumArtUri))
            }
        }



        _songsLiveData.postValue(musicList)
    }

    private fun getAlbumArtUri(albumId: Long): String {
        return "content://media/external/audio/albumart/$albumId"
    }
}

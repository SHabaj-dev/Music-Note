package com.sbz.musicnote.ui.nowPlaying

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.sbz.musicnote.databinding.FragmentNowPlayingBinding
import com.sbz.musicnote.model.MusicModel
import com.sbz.musicnote.ui.sharedViewModel.SharedViewModel


class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var isPlaying = false

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nowPlayingViewModel =
            ViewModelProvider(this)[NowPlayingViewModel::class.java]

        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.selectedSong.observe(viewLifecycleOwner) { song ->
            nowPlayingViewModel.setSelectedSong(song)
        }

        nowPlayingViewModel.selectedSong.observe(viewLifecycleOwner) { song ->
            binding.textSongName.text = song.title.toString()
            isPlaying = true
        }

        binding.btnPlayPause.setOnClickListener {
            if (isPlaying) {
                pauseSong()
            } else {
                nowPlayingViewModel.selectedSong.observe(viewLifecycleOwner) { song ->
                    playSong(song)
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
        _binding = null
    }


    fun playSong(song: MusicModel) {
        mediaPlayer.reset()
        try {
            mediaPlayer.setDataSource(song.data)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            Log.d("PLAYING_ERROR", "playSong: ${e.message}")
        }
    }

    fun pauseSong() {
        mediaPlayer.pause()
    }

    fun stopSong() {
        mediaPlayer.stop()
    }
}
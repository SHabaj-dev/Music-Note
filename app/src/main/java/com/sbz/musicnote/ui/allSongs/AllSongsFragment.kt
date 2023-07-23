package com.sbz.musicnote.ui.allSongs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sbz.musicnote.R
import com.sbz.musicnote.adapter.MusicListAdapter
import com.sbz.musicnote.databinding.FragmentAllSongsBinding
import com.sbz.musicnote.ui.nowPlaying.NowPlayingViewModel
import com.sbz.musicnote.ui.sharedViewModel.SharedViewModel

class AllSongsFragment : Fragment() {

    private var _binding: FragmentAllSongsBinding? = null
    private lateinit var allSongsViewModel: AllSongsViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val nowPlayingViewModel: NowPlayingViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        allSongsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(AllSongsViewModel::class.java)

        _binding = FragmentAllSongsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MusicListAdapter(requireContext(), emptyList())

        adapter.onItemClick = { song ->
            sharedViewModel.setSelectedSong(song)
            nowPlayingViewModel.playSong(song)
            findNavController().navigate(R.id.action_allSongsFragment_to_nowPlayingFragment)
            findNavController().popBackStack()
        }

        binding.rvSongsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSongsList.adapter = adapter

        allSongsViewModel.songsLiveData.observe(viewLifecycleOwner) { songsList ->
            adapter.submitList(songsList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
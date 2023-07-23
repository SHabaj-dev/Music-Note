package com.sbz.musicnote.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sbz.musicnote.R
import com.sbz.musicnote.model.MusicModel

class MusicListAdapter(private val context: Context, private var songsList: List<MusicModel>) :
    RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {
    var onItemClick: ((MusicModel) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTile: TextView = itemView.findViewById(R.id.tv_songName)
        val songPoster: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
//                val songName = songsList[adapterPosition].title.toString()
//                Toast.makeText(context, "$songName", Toast.LENGTH_SHORT).show()
                onItemClick?.invoke(songsList[adapterPosition])

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.songs_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSong = songsList[position]
        holder.songTile.text = currentSong.title

        Glide.with(context)
            .load(currentSong.albumArtUri)
            .into(holder.songPoster)
    }

    fun submitList(newList: List<MusicModel>) {
        songsList = newList
        notifyDataSetChanged()
    }
}
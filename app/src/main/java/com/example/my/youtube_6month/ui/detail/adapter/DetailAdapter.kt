package com.example.my.youtube_6month.ui.detail.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.my.youtube_6month.databinding.ItemDetailBinding
import com.example.my.youtube_6month.glide.loadImage
import com.example.my.youtube_6month.remote.model.Items
import com.example.my.youtube_6month.remote.model.PlaylistItem

class DetailAdapter(private val onClick: (PlaylistItem.Item) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private var playlistItems = listOf<PlaylistItem.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<PlaylistItem.Item?>?) {
        this.playlistItems = list as List<PlaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return playlistItems.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val playlistItem = playlistItems[position]
        val videoId = playlistItem.contentDetails?.videoId
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun onBind(item: PlaylistItem.Item) {
            fun onBind(item: PlaylistItem.Item, videoItem: Items?) {
                binding.image.loadImage(item.snippet?.thumbnails?.standard?.url!!)
                binding.titleTv.text = item.snippet.title
                binding.cvImage.setOnClickListener {
                    onClick.invoke(item)
                }
                if (videoItem != null) {
                    Log.e(
                        "com.example.youtube.ui.detail.DetailAdapter",
                        "onBind: ${(videoItem.contentDetails)}"
                    )
                }
            }
        }
    }

}




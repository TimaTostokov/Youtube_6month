package com.example.my.youtube_6month.ui

import androidx.lifecycle.LiveData
import com.example.my.youtube_6month.core.network.result.Resource
import com.example.my.youtube_6month.remote.model.Playlist
import com.example.my.youtube_6month.remote.model.PlaylistItem
import com.example.my.youtube_6month.remote.model.Videos
import com.example.my.youtube_6month.repository.Repository
import com.example.my.youtube_6month.uii.BaseViewModel

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return repository.getPlaylists()
    }

    fun getPlaylistItem(playlistId: String?): LiveData<Resource<PlaylistItem>> {
        return repository.getPlaylistItem(playlistId!!)
    }

    fun getVideo(id: String): LiveData<Resource<Videos>> {
        return repository.getVideo(id)
    }

}
package com.example.my.youtube_6month.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.my.youtube_6month.core.network.result.Resource
import com.example.my.youtube_6month.remote.RemoteDataSource
import com.example.my.youtube_6month.remote.model.Playlist
import com.example.my.youtube_6month.remote.model.PlaylistItem
import com.example.my.youtube_6month.remote.model.Videos
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylists()
            emit(response)
        }
    }

    fun getPlaylistItem(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylistItem(playlistId)
            emit(response)
        }
    }

    fun getVideo(id: String): LiveData<Resource<Videos>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getVideo(id)
            emit(response)
        }
    }

}
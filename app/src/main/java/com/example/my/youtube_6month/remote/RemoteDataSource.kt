package com.example.my.youtube_6month.remote

import com.example.my.youtube_6month.core.network.BaseDataSource
import com.example.my.youtube_6month.utils.Const
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(
            "\"AIzaSyAht9_KAH3Ugi24EqKLZTObGOATagWA2Z0\"",
            Const.part,
            Const.channelId
        )
    }

    suspend fun getPlaylistItem(playlistId: String?) = getResult {
        apiService.getPlaylistItem(
            "\"AIzaSyAht9_KAH3Ugi24EqKLZTObGOATagWA2Z0\"",
            Const.part,
            playlistId!!
        )
    }

    suspend fun getVideo(id: String?) = getResult {
        apiService.getVideo(
            "\"AIzaSyAht9_KAH3Ugi24EqKLZTObGOATagWA2Z0\"",
            Const.part,
            id!!
        )
    }

}
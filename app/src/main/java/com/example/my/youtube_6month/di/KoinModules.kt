package com.example.my.youtube_6month.di

import com.example.my.youtube_6month.core.network.networkModule
import com.example.my.youtube_6month.remote.remoteDataSource
import org.koin.core.module.Module

val koinModules = listOf<Module>(
    repoModules,
    viewModules,
    remoteDataSource,
    networkModule
)
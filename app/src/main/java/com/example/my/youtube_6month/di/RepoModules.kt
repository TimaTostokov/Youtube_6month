package com.example.my.youtube_6month.di

import com.example.my.youtube_6month.repository.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get()) }
}
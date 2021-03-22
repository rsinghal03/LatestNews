package com.example.latestnews.di

import com.example.latestnews.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
}
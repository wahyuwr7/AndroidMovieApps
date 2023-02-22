package com.wrseven.movieapps.resources.di

import android.content.Context
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.local.ItemDatabase
import com.wrseven.movieapps.resources.local.LocalDataSource
import com.wrseven.movieapps.resources.online.RemoteDataSource
import com.wrseven.movieapps.utils.AppExecutors

object AppInject {
    fun repositoryProvider(context: Context): ListRepository {
        val database = ItemDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.itemDao())
        val appExecutors = AppExecutors()
        return ListRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
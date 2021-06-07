package com.target.targetcasestudy.di.modules

import android.content.Context
import com.target.targetcasestudy.data.db.AppDatabase
import com.target.targetcasestudy.data.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDealsDao(db: AppDatabase) = db.dealsDao()

    @Singleton
    @Provides
    fun provideDatabaseHelper(appDatabase: AppDatabase): DatabaseHelper {
        return DatabaseHelper(appDatabase)
    }
}
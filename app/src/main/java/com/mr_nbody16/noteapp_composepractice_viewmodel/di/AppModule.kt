package com.mr_nbody16.noteapp_composepractice_viewmodel.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mr_nbody16.noteapp_composepractice_viewmodel.data.NoteDatabase
import com.mr_nbody16.noteapp_composepractice_viewmodel.data.NotesDataSource
import com.mr_nbody16.noteapp_composepractice_viewmodel.data.daos.NoteDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDbDao =
        noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context , NoteDatabase::class.java , "NotesDb")
            .fallbackToDestructiveMigration()
            .build()


}
package com.mr_nbody16.noteapp_composepractice_viewmodel.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr_nbody16.noteapp_composepractice_viewmodel.data.daos.NoteDbDao
import com.mr_nbody16.noteapp_composepractice_viewmodel.model.Note

@Database(entities = [Note::class], version = 1 , exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDbDao





}
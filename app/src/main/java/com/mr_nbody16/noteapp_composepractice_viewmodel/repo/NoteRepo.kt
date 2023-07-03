package com.mr_nbody16.noteapp_composepractice_viewmodel.repo

import com.mr_nbody16.noteapp_composepractice_viewmodel.data.NoteDatabase
import com.mr_nbody16.noteapp_composepractice_viewmodel.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepo @Inject constructor(private val noteDatabase: NoteDatabase) {

    suspend fun addNote(note : Note) = noteDatabase.noteDao().inset(note)

    suspend fun updateNote(note : Note) = noteDatabase.noteDao().update(note)

    suspend fun deleteNote(note : Note) = noteDatabase.noteDao().deleteNote(note)

    suspend fun deleteAllNotes() = noteDatabase.noteDao().deleteAll()

    suspend fun getAllNotes() : Flow<List<Note>> = noteDatabase.noteDao().getNotes().flowOn(Dispatchers.IO)
        .conflate()



}
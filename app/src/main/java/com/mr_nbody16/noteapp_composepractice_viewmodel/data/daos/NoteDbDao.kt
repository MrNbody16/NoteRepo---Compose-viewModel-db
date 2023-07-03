package com.mr_nbody16.noteapp_composepractice_viewmodel.data.daos

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mr_nbody16.noteapp_composepractice_viewmodel.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDbDao {

    @Query("SELECT * FROM notes_tbl")
    suspend fun getNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes_tbl WHERE id=:id")
    suspend fun getNotesById (id : String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inset(note : Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note : Note)

    @Query("DELETE FROM notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note : Note)




}
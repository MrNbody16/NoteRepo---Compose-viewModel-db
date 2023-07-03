package com.mr_nbody16.noteapp_composepractice_viewmodel.model

import android.os.Build
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName = "Notes_tbl")
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "entryDate")
    val entryDate: Date = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        Date.from(Instant.now())
    else
        Date()

)

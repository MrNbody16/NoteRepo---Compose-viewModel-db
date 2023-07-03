package com.mr_nbody16.noteapp_composepractice_viewmodel.screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr_nbody16.noteapp_composepractice_viewmodel.data.NotesDataSource
import com.mr_nbody16.noteapp_composepractice_viewmodel.model.Note
import com.mr_nbody16.noteapp_composepractice_viewmodel.repo.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repo : NoteRepo)  : ViewModel() {


//    private var noteList = mutableStateListOf<Note>()

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllNotes().distinctUntilChanged()
                .collect() {
                    _noteList.value = it
                }
        }
    }

    fun addNote(note : Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addNote(note)
        }
    }

    fun removeNote(note : Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteNote(note)
        }
    }

    fun updateNote(note : Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateNote(note)
        }
    }


    


}
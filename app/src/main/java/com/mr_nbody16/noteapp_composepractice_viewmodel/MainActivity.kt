package com.mr_nbody16.noteapp_composepractice_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mr_nbody16.noteapp_composepractice_viewmodel.data.NotesDataSource
import com.mr_nbody16.noteapp_composepractice_viewmodel.di.AppModule
import com.mr_nbody16.noteapp_composepractice_viewmodel.model.Note
import com.mr_nbody16.noteapp_composepractice_viewmodel.screen.NoteScreen
import com.mr_nbody16.noteapp_composepractice_viewmodel.screen.NoteViewModel
import com.mr_nbody16.noteapp_composepractice_viewmodel.ui.theme.NoteAppComposePracticeviewModelTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppComposePracticeviewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesApp()
                }
            }
        }
    }
}


@Composable
private fun NotesApp(noteViewModel: NoteViewModel = viewModel<NoteViewModel>()) {
    val notes = noteViewModel.noteList.collectAsState().value
    NoteScreen(notes = notes,
        onPassNotes = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppComposePracticeviewModelTheme {
        NotesApp()
    }
}
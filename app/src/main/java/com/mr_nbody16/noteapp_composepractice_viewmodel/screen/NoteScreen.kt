package com.mr_nbody16.noteapp_composepractice_viewmodel.screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mr_nbody16.noteapp_composepractice_viewmodel.R
import com.mr_nbody16.noteapp_composepractice_viewmodel.components.NoteButton
import com.mr_nbody16.noteapp_composepractice_viewmodel.components.NoteInputText
import com.mr_nbody16.noteapp_composepractice_viewmodel.data.NotesDataSource
import com.mr_nbody16.noteapp_composepractice_viewmodel.model.Note
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onPassNotes: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {}
) {


    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon"
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = colorResource(id = R.color.topAppbarColor))
        )

        Content(notes = notes, title = title, description = description, { newTitle ->
            Log.i("myLog", "NoteScreen: newTitle $newTitle")
            title.value = newTitle
        }, { newDescription ->
            Log.i("myLog", "NoteScreen: newDescription $newDescription")
            description.value = newDescription
        },
            onPassNotes = onPassNotes,
            onRemoveNote = onRemoveNote
        )

    }


}


@Composable
private fun Content(
    notes: List<Note> = listOf(),
    title: MutableState<String>,
    description: MutableState<String>,
    onTitleChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {},
    onPassNotes: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        NoteInputText(text = title, label = "Title", onTextChange = {
            onTitleChange(it)
        })

        NoteInputText(
            text = description,
            label = "Description",
            onTextChange = {
                onDescriptionChange(it)
            })


        val context = LocalContext.current
        NoteButton(modifier = Modifier.padding(16.dp), text = "Save", onClick = {
            if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                //save these values
                onPassNotes(Note(title = title.value, description = description.value))
                title.value = ""
                description.value = ""
                Toast.makeText(context, "NoteSaved", Toast.LENGTH_LONG).show()
            }
        })


    }


    Divider(modifier = Modifier.height(10.dp), thickness = 1.dp)

    LazyColumn {
        items(notes) { note ->
            NoteRowItemModel(note = note, onNoteLongCLicked = {
                onRemoveNote(it)
            })
        }
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NoteRowItemModel(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteLongCLicked: (Note) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(), color = Color(0xFFDFE6EB), shadowElevation = 6.dp
    ) {
        val context = LocalContext.current
        Column(modifier = modifier
            .combinedClickable(
                onLongClick = {
                    onNoteLongCLicked(note)
                },
                onClick = {

                    Toast
                        .makeText(context, "To Remove an Item LongPress it", Toast.LENGTH_LONG)
                        .show()
                }
            )
            .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleSmall)
            Text(
                /*text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM"))
                else note.entryDate.toString(),*/
                text = note.entryDate.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes())
}

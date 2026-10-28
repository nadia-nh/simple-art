package com.simpleart.journal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simpleart.journal.data.local.JournalDatabase
import com.simpleart.journal.data.repository.JournalRepositoryImpl
import com.simpleart.journal.ui.screens.HomeScreen
import com.simpleart.journal.ui.theme.SimpleArtTheme
import com.simpleart.journal.ui.viewmodel.JournalViewModel
import com.simpleart.journal.ui.viewmodel.JournalViewModelFactory

class MainActivity : ComponentActivity() {
    val database by lazy { JournalDatabase.getDatabase(this) }
    val repository by lazy { JournalRepositoryImpl(
        dao = database.journalEntryDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleArtTheme {
                val viewModel : JournalViewModel = viewModel(
                    factory = JournalViewModelFactory(repository)
                )
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

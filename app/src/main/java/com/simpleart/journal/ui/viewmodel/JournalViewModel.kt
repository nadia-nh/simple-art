package com.simpleart.journal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simpleart.journal.data.local.JournalEntryEntity
import com.simpleart.journal.data.repository.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

data class JournalUiState(
    val titleDraft: String = "",
    val contentDraft: String = "",
)

class JournalViewModel(
    private val repository: JournalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(JournalUiState())
    val uiState: StateFlow<JournalUiState> = _uiState.asStateFlow()

    val allEntries: StateFlow<List<JournalEntryEntity>> = repository.getAllEntries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
}

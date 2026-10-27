package com.simpleart.journal.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "journal_entries",
    indices = [
        Index(value = ["epochDay"], unique = true)
    ]
)
data class JournalEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val epochDay: Long,
    val content: String,
    val title: String? = null,
    val mediaUri: String? = null,
    val promptSuggestion: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

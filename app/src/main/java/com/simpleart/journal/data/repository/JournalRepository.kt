package com.simpleart.journal.data.repository

import com.simpleart.journal.data.local.JournalEntryDao
import com.simpleart.journal.data.local.JournalEntryEntity
import kotlinx.coroutines.flow.Flow

interface JournalRepository {
    fun getAllEntries(): Flow<List<JournalEntryEntity>>
    fun getEntryByDate(epochDay: Long): Flow<JournalEntryEntity?>
    suspend fun getEntryById(id: Long): JournalEntryEntity?
    fun searchEntries(query: String): Flow<List<JournalEntryEntity>>
    suspend fun saveEntry(entry: JournalEntryEntity): Long
    suspend fun deleteEntry(entry: JournalEntryEntity)
    suspend fun deleteEntryById(id: Long)
}

class JournalRepositoryImpl(
    private val dao: JournalEntryDao
) : JournalRepository {

    override fun getAllEntries(): Flow<List<JournalEntryEntity>> {
        return dao.getAllEntries()
    }

    override fun getEntryByDate(epochDay: Long): Flow<JournalEntryEntity?> {
        return dao.getEntryByDate(epochDay)
    }

    override suspend fun getEntryById(id: Long): JournalEntryEntity? {
        return dao.getEntryById(id)
    }

    override fun searchEntries(query: String): Flow<List<JournalEntryEntity>> {
        return dao.searchEntries(query)
    }

    override suspend fun saveEntry(entry: JournalEntryEntity): Long {
        return dao.upsertEntry(entry)
    }

    override suspend fun deleteEntry(entry: JournalEntryEntity) {
        dao.deleteEntry(entry)
    }

    override suspend fun deleteEntryById(id: Long) {
        dao.deleteEntryById(id)
    }
}

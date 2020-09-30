package com.example.roomwordsample.Repository

import androidx.lifecycle.LiveData
import com.example.roomwordsample.DAO.WordDao
import com.example.roomwordsample.Entities.Word

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun deleteAll() {
        wordDao.deleteAll()
    }

    suspend fun delete(word: String) {
        wordDao.delete(word)
    }
}
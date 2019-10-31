package com.bcarbonesi.androidroomwithaview.data

import androidx.lifecycle.LiveData
import com.bcarbonesi.androidroomwithaview.model.WordDAO

class WordRepository (private val wordDAO: WordDAO) {

    val allWords : LiveData<List<Word>> = wordDAO.getAlphabetizedWords()

    suspend fun insert(word : Word) {
        wordDAO.insert(word)
    }
}
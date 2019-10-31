package com.bcarbonesi.androidroomwithaview.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bcarbonesi.androidroomwithaview.data.Word
import com.bcarbonesi.androidroomwithaview.data.WordRepository
import com.bcarbonesi.androidroomwithaview.data.WordRoomDataBase
import kotlinx.coroutines.launch

class WordViewModel (application: Application) : AndroidViewModel(application) {

    private val repository : WordRepository

    val allWords : LiveData<List<Word>>

    init {
        val wordsDAO = WordRoomDataBase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDAO)
        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch {

        repository.insert(word)

    }


}
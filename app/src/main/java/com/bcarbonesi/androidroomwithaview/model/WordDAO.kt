package com.bcarbonesi.androidroomwithaview.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bcarbonesi.androidroomwithaview.data.Word


@Dao
interface WordDAO {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords() : LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}
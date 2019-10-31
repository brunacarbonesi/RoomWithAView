package com.bcarbonesi.androidroomwithaview.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bcarbonesi.androidroomwithaview.model.WordDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1)
abstract class WordRoomDataBase : RoomDatabase() {

    abstract fun wordDao() : WordDAO

    private class WordDataBaseCallback (private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database -> scope.launch {
                var wordDAO = database.wordDao()

                wordDAO.deleteAll()

                var word = Word("Hello")
                wordDAO.insert(word)

                word = Word("World")
                wordDAO.insert(word)

                word = Word("Bruna")
                wordDAO.insert(word)
            } }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : WordRoomDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : WordRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    WordRoomDataBase::class.java, "word_database")
                    .addCallback(WordDataBaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
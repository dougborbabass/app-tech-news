package br.com.douglas.technews.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.douglas.technews.database.dao.NoticiaDAO
import br.com.douglas.technews.model.Noticia

private const val NOME_BANCO_DE_DADOS = "news.db"

@Database(entities = [Noticia::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val noticiaDAO: NoticiaDAO

    companion object {

        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {

            if (::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
            ).build()

            return db
        }
    }
}
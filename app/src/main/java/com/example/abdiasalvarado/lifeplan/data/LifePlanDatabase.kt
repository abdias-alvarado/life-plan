package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Tabla_Actividades::class], version = 2, exportSchema = false)
abstract class LifePlanDatabase: RoomDatabase() {
    /**
     * Este es un método abstracto que retorna el DAO para la base de datos.
     */
    abstract fun getTablaActividadesDao(): Tabla_Actividades_Dao

    /**
     * Un patrón de diseño Singleton es utilizado para asegurarnos que
     * solamente se cree una instancia de la base de datos.
     */
    companion object {
        val nombreDatabase = "lifeplan_db"
        var lifePlanDatabase: LifePlanDatabase? = null

        fun getInstance(context: Context): LifePlanDatabase? {
            if (lifePlanDatabase == null) {
                lifePlanDatabase = Room.databaseBuilder(context,
                    LifePlanDatabase::class.java,
                    LifePlanDatabase.nombreDatabase)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return lifePlanDatabase
        }
    }
}
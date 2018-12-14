package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = [Tabla_Actividades::class, Tabla_Recordatorios::class, Tabla_Metas::class], version = 2, exportSchema = false)
abstract class LifePlanDatabase: RoomDatabase() {

    abstract fun getTablaActividadesDao(): Tabla_Actividades_Dao
    abstract fun getTablaRecordatoriosDao(): Tabla_Recordatorios_Dao
    abstract fun getTablaMetasDao(): Tabla_Metas_Dao

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
package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.*

@Dao
interface Tabla_Recordatorios_Dao {

    @Query("SELECT * FROM Recordatorios ORDER BY id ASC")
    fun consultaRecordatorios(): List<Tabla_Recordatorios>


    @Query("SELECT * FROM Recordatorios WHERE id = :id")
    fun consultaRecordatorio(id: Int): Tabla_Recordatorios


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarRecordatorio(recordatorio: Tabla_Recordatorios)

    @Update
    fun actualizarRecordatorio(recordatorio: Tabla_Recordatorios)


    @Delete
    fun eliminarRecordatorio(recordatorio: Tabla_Recordatorios)
}
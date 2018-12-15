package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.*

@Dao
interface Tabla_Actividades_Dao {

    @Query("SELECT * FROM Actividades ORDER BY id ASC")
    fun consultaActividades(): List<Tabla_Actividades>


    @Query("SELECT * FROM Actividades WHERE id = :id")
    fun consultaActividad(id: Int): Tabla_Actividades

    @Query("SELECT * FROM Actividades  WHERE meta = :meta")
    fun consultaActividadesPorMeta(meta: String): List<Tabla_Actividades>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarActividad(actividad: Tabla_Actividades)

    @Update
    fun actualizarActividad(actividad: Tabla_Actividades)


    @Delete
    fun eliminarActividad(actividad: Tabla_Actividades)


    @Query("DELETE FROM Actividades  WHERE meta = :meta")
    fun eliminarActividadesMeta(meta: String)

    @Query("UPDATE Actividades SET meta = :metanueva WHERE meta = :metavieja")
    fun actualizarActividadesMeta(metavieja: String, metanueva: String)
}
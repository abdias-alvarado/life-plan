package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.*

@Dao
interface Tabla_Metas_Dao {

    @Query("SELECT * FROM Metas ORDER BY id ASC")
    fun consultaMetas(): List<Tabla_Metas>


    @Query("SELECT * FROM Metas WHERE id = :id")
    fun consultaMeta(id: Int): Tabla_Metas

    @Query("SELECT etiqueta FROM Metas ORDER BY id ASC")
    fun consultaMetasString(): List<String>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarMeta(meta: Tabla_Metas)

    @Update
    fun actualizarMeta(meta: Tabla_Metas)


    @Delete
    fun eliminarMeta(meta: Tabla_Metas)
}
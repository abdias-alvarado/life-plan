package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Actividades")
class Tabla_Actividades
    (
    @ColumnInfo(name = "etiqueta") var etiqueta: String = "",
    @ColumnInfo(name = "hora")  var hora: String = "",
    @ColumnInfo(name = "categoria") var categoria: String = "",
    @ColumnInfo(name = "completado")  var completado: Boolean = false
)
{
    @PrimaryKey(autoGenerate = true)   var id: Int = 0
}
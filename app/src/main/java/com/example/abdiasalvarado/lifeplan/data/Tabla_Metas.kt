package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Metas")
class Tabla_Metas
    (
    @ColumnInfo(name = "etiqueta") var etiqueta: String = "",
    @ColumnInfo(name = "fecha_inicio")  var fecha_inicio: String = "",
    @ColumnInfo(name = "fecha_final")  var fecha_final: String = "",
    @ColumnInfo(name = "completado")  var completado: Boolean = false
)
{
    @PrimaryKey(autoGenerate = true)   var id: Int = 0
}
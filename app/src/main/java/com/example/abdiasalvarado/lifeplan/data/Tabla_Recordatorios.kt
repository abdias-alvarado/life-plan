package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Recordatorios")
class Tabla_Recordatorios
    (
    @ColumnInfo(name = "descripcion") var descripcion: String = "",
    @ColumnInfo(name = "fecha")  var fecha: String = "",
    @ColumnInfo(name = "hora")  var hora: String = "",
    @ColumnInfo(name = "completado")  var completado: Boolean = false

)
{
    @PrimaryKey(autoGenerate = true)   var id: Int = 0
}

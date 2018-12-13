package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Usuarios")
class Tabla_Usuarios
    (
    @ColumnInfo(name = "nombre") var nombre: String = "",
    @ColumnInfo(name = "correo")  var correo: String = "",
    @ColumnInfo(name = "password")  var password: String = ""

)
{
    @PrimaryKey(autoGenerate = true)   var id: Int = 0
}
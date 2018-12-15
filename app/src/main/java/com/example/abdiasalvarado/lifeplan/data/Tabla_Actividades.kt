package com.example.abdiasalvarado.lifeplan.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.abdiasalvarado.lifeplan.R

@Entity(tableName = "Actividades")
class Tabla_Actividades
    (
    @ColumnInfo(name = "etiqueta") var etiqueta: String = "",
    @ColumnInfo(name = "hora")  var hora: String = "",
    @ColumnInfo(name = "categoria") var categoria: String = "",
    @ColumnInfo(name = "meta")  var meta: String = "",
    @ColumnInfo(name = "completado")  var completado: Boolean = false

)
{
    @PrimaryKey(autoGenerate = true)   var id: Int = 0
    var imagen : Int = 0


    fun getImgId() : Int{
        if(categoria == "Ejercicio") {
            imagen = R.drawable.ic_icono_color_ejercicio
        }
        else if(categoria == "Alimentación")
        {
            imagen = R.drawable.ic_icono_color_alimentos
        }
        else if(categoria == "Académico")
        {
            imagen = R.drawable.ic_icono_color_academico
        }
        else if(categoria == "Ocio")
        {
            imagen = R.drawable.ic_icono_color_ocio
        }
        else if(categoria == "Deportes")
        {
            imagen = R.drawable.ic_icono_color_deportes
        }
        else if(categoria == "Social")
        {
            imagen = R.drawable.ic_icono_color_social
        }
        else if(categoria == "Otro")
        {
            imagen = R.drawable.ic_icono_color_otro
        }

        return imagen
    }

}
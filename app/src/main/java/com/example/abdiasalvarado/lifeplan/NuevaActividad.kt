package com.example.abdiasalvarado.lifeplan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades
import kotlinx.android.synthetic.main.activity_nueva_actividad.*


class NuevaActividad : AppCompatActivity() {

    private var lifeplanDatabase: LifePlanDatabase? = null
    private var prioridad = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_actividad)

        lifeplanDatabase = LifePlanDatabase.getInstance(this)


        // Validar si se nos envía el intent con el valor del título
        val etiqueta = intent.getStringExtra("etiqueta")
        val hora = intent.getStringExtra("hora")

        // Si no está definido o viene en blanco, el usuario presionó el FAB
        if (etiqueta == null || etiqueta == "") {
            btnAgregar.setOnClickListener {
                val actividad = Tabla_Actividades(etEtiqueta.text.toString(), etHora.text.toString())
                lifeplanDatabase?.getTablaActividadesDao()?.insertarActividad(actividad)
                finish()
            }
        } else {
            val id = intent.getIntExtra("id", 0)
            etEtiqueta.setText(etiqueta)
            etHora.setText(hora)
            btnAgregar.setOnClickListener {
                val actividad = Tabla_Actividades(etEtiqueta.text.toString(), etHora.text.toString())
                actividad.id = id
                lifeplanDatabase?.getTablaActividadesDao()?.actualizarActividad(actividad)
                finish()
            }
        }
    }


}

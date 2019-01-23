package com.example.abdiasalvarado.lifeplan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase

import com.example.abdiasalvarado.lifeplan.data.Tabla_Metas

import kotlinx.android.synthetic.main.activity_nueva_meta.*

class NuevaMeta : AppCompatActivity() {
    private var lifeplanDatabase: LifePlanDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_meta)

        lifeplanDatabase = LifePlanDatabase.getInstance(this)



        val etiqueta = intent.getStringExtra("etiqueta")
        val fechaInicio = intent.getStringExtra("fecha_inicio")
        val fechaFinal = intent.getStringExtra("fecha_final")


        if (etiqueta == null || etiqueta == "") {
            btnAgregar.setOnClickListener {

                val meta = Tabla_Metas(etEtiqueta.text.toString(), etFechaInicio.text.toString(), etFechaFinal.text.toString())
                lifeplanDatabase?.getTablaMetasDao()?.insertarMeta(meta)

                finish()
            }
        } else {
            val id = intent.getIntExtra("id", 0)
            etEtiqueta.setText(etiqueta)
            etFechaInicio.setText(fechaInicio)
            etFechaFinal.setText(fechaFinal)
            btnAgregar.setOnClickListener {
                val meta = Tabla_Metas(etEtiqueta.text.toString(), etFechaInicio.text.toString(), etFechaFinal.text.toString())
                lifeplanDatabase?.getTablaMetasDao()?.actualizarMeta(meta)
                lifeplanDatabase?.getTablaActividadesDao()?.actualizarActividadesMeta(etiqueta, meta.etiqueta)

                finish()
            }
        }
    }
}

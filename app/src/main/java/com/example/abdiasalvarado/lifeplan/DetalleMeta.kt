package com.example.abdiasalvarado.lifeplan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades
import kotlinx.android.synthetic.main.activity_detalle_meta.*
import kotlinx.android.synthetic.main.fragment_plantilla_metas.*

class DetalleMeta : AppCompatActivity(), Adaptador_Actividades.OnItemActividadClickListener{


    private var lifePlanDatabase: LifePlanDatabase? = null
    private var actividadesAdapter: Adaptador_Actividades? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_meta)

        var meta = intent.getStringExtra("etiqueta")

        lifePlanDatabase = LifePlanDatabase.getInstance(this)
        actividadesAdapter = Adaptador_Actividades(lifePlanDatabase?.getTablaActividadesDao()?.consultaActividadesPorMeta(meta))
        actividadesAdapter?.setTodoItemClickListener(this)

        fabAgregar.setOnClickListener {
            startActivity(Intent(this, NuevaActividad::class.java))
        }


    }

    override fun onResume() {
        super.onResume()
        var meta = intent.getStringExtra("etiqueta")
        actividadesAdapter?.listaActividades = lifePlanDatabase?.getTablaActividadesDao()?.consultaActividadesPorMeta(meta)
        rvActividades_Meta.adapter = actividadesAdapter
        rvActividades_Meta.layoutManager = LinearLayoutManager(this)
        rvActividades_Meta.hasFixedSize()
    }



    override fun onItemActividadClickListener(actividad: Tabla_Actividades) {
        val intent = Intent(this, NuevaActividad::class.java)
        intent.putExtra("id", actividad.id)
        intent.putExtra("etiqueta", actividad.etiqueta)
        intent.putExtra("hora", actividad.hora)
        startActivity(intent)
    }

    override fun onItemActividadLongClickListener(actividad: Tabla_Actividades) {
        // Inicializar una nueva instancia de AlertDialog
        val builder = AlertDialog.Builder(this)

        // Colocar el titulo del dialogo
        builder.setTitle("Seleccione una:")

        // Mensaje a desplegar en el dialogo
        builder.setMessage("Elija una opción a realizar:")

        // Los dialogos pueden tener hasta 3 botones, uno positivo (SI), uno negativo (NO)
        // y un boton neutro (CANCEL) los cuales utilizaremos para Modificar, Eliminar y Cancelar
        builder.setPositiveButton("Modificar") {dialog, wich ->

            val intent = Intent(this, NuevaActividad::class.java)
            intent.putExtra("id", actividad.id)
            intent.putExtra("etiqueta", actividad.etiqueta)
            intent.putExtra("hora", actividad.hora)
            startActivity(intent)
        }

        builder.setNegativeButton("Eliminar") {dialog, which ->
            lifePlanDatabase?.getTablaActividadesDao()?.eliminarActividad(actividad)
            onResume()
            Toast.makeText(this, "Actividad eliminada.", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelando.", Toast.LENGTH_SHORT).show()
        }

        // Crear el dialogo de alerta con todos los parámetros establecidos
        val dialogo: AlertDialog = builder.create()

        // Mostrar el dialogo
        dialogo.show()
    }
}

package com.example.abdiasalvarado.lifeplan


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades
import kotlinx.android.synthetic.main.activity_detalle_meta.*
import kotlinx.android.synthetic.main.fragment_tablero_principal.*


class TableroActivity : Fragment(), Adaptador_Actividades.OnItemActividadClickListener {


    private var lifePlanDatabase: LifePlanDatabase? = null
    private var actividadesAdapter: Adaptador_Actividades? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var vista = inflater.inflate(R.layout.fragment_tablero_principal, container, false)

        lifePlanDatabase = LifePlanDatabase.getInstance(context!!)
        actividadesAdapter = Adaptador_Actividades(lifePlanDatabase?.getTablaActividadesDao()?.consultaActividades())
        actividadesAdapter?.setTodoItemClickListener(this)

        return vista



    }

    override fun onResume() {
        super.onResume()
        actividadesAdapter?.listaActividades = lifePlanDatabase?.getTablaActividadesDao()?.consultaActividades()
        rvTablero.adapter = actividadesAdapter
        rvTablero.layoutManager = LinearLayoutManager(context)
        rvTablero.hasFixedSize()
    }

    override fun onItemActividadClickListener(actividad: Tabla_Actividades) {
        val intent = Intent(context, NuevaActividad::class.java)
        intent.putExtra("id", actividad.id)
        intent.putExtra("etiqueta", actividad.etiqueta)
        intent.putExtra("hora", actividad.hora)
        intent.putExtra("categoria", actividad.categoria)
        startActivity(intent)
    }

    override fun onItemActividadLongClickListener(actividad: Tabla_Actividades) {

        val builder = AlertDialog.Builder(context!!)

        builder.setTitle("MENU DE OPCIONES")

        builder.setMessage("¿Qué desea realizar?")

        builder.setPositiveButton("Modificar") {dialog, wich ->

            val intent = Intent(context, NuevaActividad::class.java)
            intent.putExtra("id", actividad.id)
            intent.putExtra("etiqueta", actividad.etiqueta)
            intent.putExtra("hora", actividad.hora)
            intent.putExtra("categoria", actividad.categoria)
            startActivity(intent)
        }

        builder.setNegativeButton("Eliminar") {dialog, which ->
            lifePlanDatabase?.getTablaActividadesDao()?.eliminarActividad(actividad)
            onResume()
            Toast.makeText(context, "Actividad eliminada.", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Cancelar") {dialog, which ->
            Toast.makeText(context, "Cancelando.", Toast.LENGTH_SHORT).show()
        }

        val dialogo: AlertDialog = builder.create()
        dialogo.show()
    }
}



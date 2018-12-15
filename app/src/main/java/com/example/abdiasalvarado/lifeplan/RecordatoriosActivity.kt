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
import com.example.abdiasalvarado.lifeplan.data.Tabla_Recordatorios
import kotlinx.android.synthetic.main.fragment_recordatorios.*

class RecordatoriosActivity : Fragment(), Adaptador_Recordatorios.OnItemRecordatoriosClickListener {

    private var lifePlanDatabase: LifePlanDatabase? = null
    private var recordatoriosAdapter: Adaptador_Recordatorios? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var vista = inflater.inflate(R.layout.fragment_recordatorios, container, false)
        lifePlanDatabase = LifePlanDatabase.getInstance(context!!)
        recordatoriosAdapter = Adaptador_Recordatorios(lifePlanDatabase?.getTablaRecordatoriosDao()?.consultaRecordatorios())
        recordatoriosAdapter?.setItemRecordatorioClickListener(this)

        return vista


    }

    override fun onResume() {
        super.onResume()
        recordatoriosAdapter?.listaRecordatorios = lifePlanDatabase?.getTablaRecordatoriosDao()?.consultaRecordatorios()
        rvRecordatorios.adapter = recordatoriosAdapter
        rvRecordatorios.layoutManager = LinearLayoutManager(context)
        rvRecordatorios.hasFixedSize()
    }

    override fun onItemRecordatorioClickListener(recordatorio: Tabla_Recordatorios) {
        val intent = Intent(context, NuevaActividad::class.java)
        intent.putExtra("id", recordatorio.id)
        intent.putExtra("etiqueta", recordatorio.descripcion)
        intent.putExtra("hora", recordatorio.hora)
        intent.putExtra("categoria", recordatorio.categoria)
        startActivity(intent)
    }

    override fun onItemRecordatorioLongClickListener(recordatorio: Tabla_Recordatorios) {

        val builder = AlertDialog.Builder(context!!)

        builder.setTitle("MENU DE OPCIONES")

        builder.setMessage("¿Qué desea realizar?")

        builder.setPositiveButton("Modificar") { dialog, wich ->

            val intent = Intent(context, NuevaActividad::class.java)
            intent.putExtra("id", recordatorio.id)
            intent.putExtra("etiqueta", recordatorio.descripcion)
            intent.putExtra("hora", recordatorio.hora)
            intent.putExtra("categoria", recordatorio.categoria)
            startActivity(intent)
        }

        builder.setNegativeButton("Eliminar") { dialog, which ->
            lifePlanDatabase?.getTablaRecordatoriosDao()?.eliminarRecordatorio(recordatorio)
            onResume()
            Toast.makeText(context, "Actividad eliminada.", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Cancelar") { dialog, which ->
            Toast.makeText(context, "Cancelando.", Toast.LENGTH_SHORT).show()
        }

        val dialogo: AlertDialog = builder.create()
        dialogo.show()
    }
}
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
import com.example.abdiasalvarado.lifeplan.data.Tabla_Metas
import kotlinx.android.synthetic.main.fragment_metas.*

class MetasActivity : Fragment(), Adaptador_Metas.OnItemMetaClickListener{


    private var lifePlanDatabase: LifePlanDatabase? = null
    private var metasAdapter: Adaptador_Metas? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var vista = inflater.inflate(R.layout.fragment_metas, container, false)

        lifePlanDatabase = LifePlanDatabase.getInstance(context!!)
        metasAdapter = Adaptador_Metas(lifePlanDatabase?.getTablaMetasDao()!!.consultaMetas())
        metasAdapter?.setTodoItemClickListener(this)

        return vista
    }


    override fun onResume() {
        super.onResume()
        metasAdapter?.listaMetas = lifePlanDatabase?.getTablaMetasDao()?.consultaMetas()
        rvMetas.adapter = metasAdapter
        rvMetas.layoutManager = LinearLayoutManager(context)
        rvMetas.hasFixedSize()
    }

    override fun onItemMetaClickListener(meta: Tabla_Metas) {
        val intent = Intent(context, DetalleMeta::class.java)
        intent.putExtra("id", meta.id)
        intent.putExtra("etiqueta", meta.etiqueta)
        intent.putExtra("fecha_final", meta.fecha_inicio)
        intent.putExtra("fecha_inicio", meta.fecha_final)
        startActivity(intent)
    }

    override fun onItemMetaLongClickListener(meta: Tabla_Metas) {
        val builder = AlertDialog.Builder(context!!)

        builder.setTitle("MENU DE OPCIONES")

        builder.setMessage("¿Qué desea realizar?")

        builder.setPositiveButton("Modificar") {dialog, wich ->

            val intent = Intent(context, NuevaMeta::class.java)
            intent.putExtra("id", meta.id)
            intent.putExtra("etiqueta", meta.etiqueta)
            intent.putExtra("fecha_final", meta.fecha_inicio)
            intent.putExtra("fecha_inicio", meta.fecha_final)
            startActivity(intent)
        }

        builder.setNegativeButton("Eliminar") {dialog, which ->
            lifePlanDatabase?.getTablaActividadesDao()?.eliminarActividadesMeta(meta.etiqueta)
            lifePlanDatabase?.getTablaMetasDao()?.eliminarMeta(meta)
            onResume()
            Toast.makeText(context, "Meta eliminada.", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Cancelar") {dialog, which ->
            Toast.makeText(context, "Cancelando.", Toast.LENGTH_SHORT).show()
        }

        val dialogo: AlertDialog = builder.create()
        dialogo.show()
    }



}
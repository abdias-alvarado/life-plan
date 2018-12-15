package com.example.abdiasalvarado.lifeplan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.abdiasalvarado.lifeplan.data.Tabla_Recordatorios


class Adaptador_Recordatorios(var listaRecordatorios: List<Tabla_Recordatorios>? = ArrayList<Tabla_Recordatorios>()): RecyclerView.Adapter<Adaptador_Recordatorios.ViewHolder>()
{
    private var onItemRecordatoriosClickListener: OnItemRecordatoriosClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Adaptador_Recordatorios.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fragment_plantilla_recordatorios, parent, false)

        return ViewHolder(vista, listaRecordatorios!!)
    }

    override fun getItemCount(): Int {
        return listaRecordatorios?.count()!!
    }

    override fun onBindViewHolder(holder: Adaptador_Recordatorios.ViewHolder, position: Int) {
        // Obtener la posición del item clickeado
        holder.vista.setOnClickListener{
            onItemRecordatoriosClickListener?.onItemRecordatorioClickListener(listaRecordatorios?.get(position)!!)
        }
        holder.vista.setOnLongClickListener{
            onItemRecordatoriosClickListener?.onItemRecordatorioLongClickListener(listaRecordatorios?.get(position)!!)
            true
        }
        holder.onBindViews(position)
    }

    class ViewHolder(val vista: View, val recordatoriosList: List<Tabla_Recordatorios>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvTitulo).text = recordatoriosList.get(position).descripcion
            vista.findViewById<TextView>(R.id.tvHora).text = recordatoriosList.get(position).hora
            vista.findViewById<ImageView>(R.id.ivIcono).setImageResource(recordatoriosList[position].getImgId())

        }


    }

    fun setItemRecordatorioClickListener(onItemRecordatoriosClickListener: OnItemRecordatoriosClickListener) {
        this.onItemRecordatoriosClickListener = onItemRecordatoriosClickListener
    }

    interface OnItemRecordatoriosClickListener {
        fun onItemRecordatorioClickListener(recordatorio: Tabla_Recordatorios)
        fun onItemRecordatorioLongClickListener(recordatorio: Tabla_Recordatorios)
    }
}
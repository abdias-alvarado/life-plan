package com.example.abdiasalvarado.lifeplan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.abdiasalvarado.lifeplan.data.Tabla_Metas


class Adaptador_Metas(var listaMetas: List<Tabla_Metas>? = ArrayList<Tabla_Metas>()): RecyclerView.Adapter<Adaptador_Metas.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Adaptador_Metas.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fragment_plantilla_metas, parent, false)

        return ViewHolder(vista, listaMetas!!)
    }

    override fun getItemCount(): Int {
        return listaMetas?.count()!!
    }

    override fun onBindViewHolder(holder: Adaptador_Metas.ViewHolder, position: Int) {
        holder.vista.setOnClickListener{
            onItemMetaClickListener?.onItemMetaClickListener(listaMetas?.get(position)!!)
        }
        holder.vista.setOnLongClickListener{
            onItemMetaClickListener?.onItemMetaLongClickListener(listaMetas?.get(position)!!)
            true
        }
        holder.onBindViews(position)
    }


    private var onItemMetaClickListener: OnItemMetaClickListener? = null


    class ViewHolder(val vista: View, val listaMetas: List<Tabla_Metas>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvEtiquetaMeta).text = listaMetas.get(position).etiqueta
            vista.findViewById<TextView>(R.id.tvFechaInicio).text = listaMetas.get(position).fecha_inicio
            vista.findViewById<TextView>(R.id.tvFechaFinal).text = listaMetas.get(position).fecha_inicio

        }


    }

    fun setTodoItemClickListener(onItemMetaClickListener: OnItemMetaClickListener) {
        this.onItemMetaClickListener = onItemMetaClickListener
    }

    interface OnItemMetaClickListener {
        fun onItemMetaClickListener(meta: Tabla_Metas)
        fun onItemMetaLongClickListener(meta: Tabla_Metas)
    }
}
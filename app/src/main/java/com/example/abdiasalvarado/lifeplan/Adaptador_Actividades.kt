package com.example.abdiasalvarado.lifeplan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades


class Adaptador_Actividades(var listaActividades: List<Tabla_Actividades>? = ArrayList<Tabla_Actividades>()): RecyclerView.Adapter<Adaptador_Actividades.ViewHolder>()
{
    private var onItemActividadClickListener: OnItemActividadClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Adaptador_Actividades.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fragment_plantilla_tablero, parent, false)

        return ViewHolder(vista, listaActividades!!)
    }

    override fun getItemCount(): Int {
        return listaActividades?.count()!!
    }

    override fun onBindViewHolder(holder: Adaptador_Actividades.ViewHolder, position: Int) {
        // Obtener la posición del item clickeado
        holder.vista.setOnClickListener{
            onItemActividadClickListener?.onItemActividadClickListener(listaActividades?.get(position)!!)
        }
        holder.vista.setOnLongClickListener{
            onItemActividadClickListener?.onItemActividadLongClickListener(listaActividades?.get(position)!!)
            true
        }
        holder.onBindViews(position)
    }

    class ViewHolder(val vista: View, val todoList: List<Tabla_Actividades>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvTitulo).text = todoList.get(position).etiqueta
            vista.findViewById<TextView>(R.id.tvHora).text = todoList.get(position).hora
            vista.findViewById<ImageView>(R.id.ivIcono).setImageResource(todoList[position].getImgId())

        }


    }

    fun setTodoItemClickListener(onItemActividadClickListener: OnItemActividadClickListener) {
        this.onItemActividadClickListener = onItemActividadClickListener
    }

    interface OnItemActividadClickListener {
        fun onItemActividadClickListener(actividad: Tabla_Actividades)
        fun onItemActividadLongClickListener(actividad: Tabla_Actividades)
    }
}
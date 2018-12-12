package com.example.abdiasalvarado.lifeplan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades


class Adaptador_Actividades(var listaActividades: List<Tabla_Actividades>? = ArrayList<Tabla_Actividades>()): RecyclerView.Adapter<Adaptador_Actividades.ViewHolder>()
{
    private var onActividadItemClickListener: OnTodoItemClickListener? = null

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
            onActividadItemClickListener?.onTodoItemClickListener(listaActividades?.get(position)!!)
        }
        holder.vista.setOnLongClickListener{
            onActividadItemClickListener?.onTodoItemLongClickListener(listaActividades?.get(position)!!)
            true
        }
        holder.onBindViews(position)
    }

    class ViewHolder(val vista: View, val todoList: List<Tabla_Actividades>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvTitulo).text = todoList.get(position).etiqueta
            vista.findViewById<TextView>(R.id.tvHora).text = todoList.get(position).hora

        }


    }

    /**
     * Utilizamos un método en lugar de asignar la funcionalidad vía el constructor
     * de la clase principal para mayor comodidad al momento de sobreescribir la funcionalidad.
     */
    fun setTodoItemClickListener(onTodoItemClickListener: OnTodoItemClickListener) {
        this.onActividadItemClickListener = onTodoItemClickListener
    }

    /**
     * Definimos la interface que permite extender métodos que el RecyclerView no posee
     */
    interface OnTodoItemClickListener {
        fun onTodoItemClickListener(todo: Tabla_Actividades)
        fun onTodoItemLongClickListener(todo: Tabla_Actividades)
    }
}
package com.example.abdiasalvarado.lifeplan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades
import kotlinx.android.synthetic.main.fragment_metas.*

class MetasActivity : Fragment(), Adaptador_Actividades.OnTodoItemClickListener{


    private var todoDatabase: LifePlanDatabase? = null
    private var todoAdapter: Adaptador_Actividades? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_metas, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoAdapter = Adaptador_Actividades(todoDatabase?.getTablaActividadesDao()?.consultaActividades())
        todoAdapter?.setTodoItemClickListener(this)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        todoDatabase = LifePlanDatabase.getInstance(c)
//        todoAdapter = Adaptador_Actividades(todoDatabase?.getTablaActividadesDao()?.consultaActividades())
//        todoAdapter?.setTodoItemClickListener(this)
//        todoAdapter?.listaActividades = todoDatabase?.getTablaActividadesDao()?.consultaActividades()
//        rvMetas.adapter = todoAdapter
//        rvMetas.layoutManager = LinearLayoutManager(this)
//        rvMetas.hasFixedSize()
//    }

    override fun onResume() {
        super.onResume()
        todoAdapter?.listaActividades = todoDatabase?.getTablaActividadesDao()?.consultaActividades()
        rvMetas.adapter = todoAdapter
//        rvMetas.layoutManager = LinearLayoutManager(this)
        rvMetas.hasFixedSize()
    }

    override fun onTodoItemClickListener(todo: Tabla_Actividades) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodoItemLongClickListener(todo: Tabla_Actividades) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
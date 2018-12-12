package com.example.abdiasalvarado.lifeplan

import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades

import kotlinx.android.synthetic.main.activity_tablero_principal.*
import kotlinx.android.synthetic.main.fragment_tablero_principal.view.*

class TableroPrincipal : AppCompatActivity(), Adaptador_Actividades.OnTodoItemClickListener {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private var todoDatabase: LifePlanDatabase? = null
    private var todoAdapter: Adaptador_Actividades? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablero_principal)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))


//        todoDatabase = LifePlanDatabase.getInstance(this)
//        todoAdapter = Adaptador_Actividades(todoDatabase?.getTablaActividadesDao()?.consultaActividades())
//        todoAdapter?.setTodoItemClickListener(this)



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    override fun onResume() {
        super.onResume()
        todoAdapter?.listaActividades = todoDatabase?.getTablaActividadesDao()?.consultaActividades()
//        reci.adapter = todoAdapter
//        rvToDo.layoutManager = LinearLayoutManager(this)
//        rvToDo.hasFixedSize()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_tablero_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
           when (position){
               0 -> {
                   return TableroActivity()
               }
               1 -> {
                   return MetasActivity()
               }
               2 -> {
                   return AgendaActivity()
               }
               3 -> {
                   return CalendarioActivity()
               }
               4 -> {
                   return RecordatoriosActivity()
               }
               else -> return null
           }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 5
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0 -> return "TABLERO"
                1 -> return "METAS"
                2 -> return "AGENDA"
                3 -> return "CALENDARIO"
                4 -> return "RECORDATORIOS"
            }
            return null
        }
    }

    override fun onTodoItemClickListener(todo: Tabla_Actividades) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodoItemLongClickListener(todo: Tabla_Actividades) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

package com.example.abdiasalvarado.lifeplan

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades
import com.facebook.CallbackManager

import kotlinx.android.synthetic.main.activity_tablero_principal.*
import kotlinx.android.synthetic.main.fragment_tablero_principal.view.*
import com.facebook.login.LoginManager
import com.facebook.GraphResponse
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.AccessToken
import java.security.AccessController.getContext


class TableroPrincipal : AppCompatActivity(){

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablero_principal)

        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))




        fab.setOnClickListener { view ->

            if(container.currentItem.toString() == "0")
            {
                var intent = Intent(this, NuevaActividad::class.java)
                startActivity(intent)
            }
            else if(container.currentItem.toString() == "1")
            {
                var intent = Intent(this, NuevaMeta::class.java)
                startActivity(intent)
            }
            else if(container.currentItem.toString() == "2")
            {
                var intent = Intent(this, NuevaActividad::class.java)
                startActivity(intent)
            }

        }

    }

    fun cerrarCesion(context: Context){
        val builder = AlertDialog.Builder(context!!)

        builder.setTitle("SALIR")

        builder.setMessage("¿Está seguro que desea salir?")

        builder.setPositiveButton("Salir") {dialog, wich ->

            LoginManager.getInstance().logOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

        builder.setNeutralButton("Cancelar") {dialog, which ->
            Toast.makeText(context, "Cancelando.", Toast.LENGTH_SHORT).show()
        }

        val dialogo: AlertDialog = builder.create()
        dialogo.show()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_tablero_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_salir) {
            cerrarCesion(this)
        }

        return super.onOptionsItemSelected(item)
    }

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
                   return RecordatoriosActivity()
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
            return 4
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0 -> return "TABLERO"
                1 -> return "METAS"
                2 -> return "RECORDATORIOS"
                3 -> return "CALENDARIO"
                4 -> return "AGENDA"
            }
            return null
        }



    }



}

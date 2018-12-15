package com.example.abdiasalvarado.lifeplan

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.abdiasalvarado.lifeplan.data.LifePlanDatabase
import com.example.abdiasalvarado.lifeplan.data.Tabla_Actividades
import com.example.abdiasalvarado.lifeplan.data.Tabla_Recordatorios
import kotlinx.android.synthetic.main.activity_nueva_actividad.*


class NuevaActividad : AppCompatActivity(){

    private var lifeplanDatabase: LifePlanDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_actividad)

        fabHora.setOnClickListener {
            val timePicker = TimePicker()
            timePicker.show(supportFragmentManager, "SELECTOR HORA")
        }
        lifeplanDatabase = LifePlanDatabase.getInstance(this)

        val listaMetas = lifeplanDatabase?.getTablaMetasDao()?.consultaMetasString()



        val adapterCategoria = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            listOf("Recordatorio","Ejercicio", "Alimentación", "Académico", "Ocio", "Deportes", "Social", "Otra"))
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterMetas = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            listaMetas)
        adapterMetas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sprCategoria.adapter = adapterCategoria
        sprMeta.adapter = adapterMetas


        val etiqueta = intent.getStringExtra("etiqueta")
        val hora = intent.getStringExtra("hora")
        val categoria = intent.getStringExtra("categoria")
        val meta = intent.getStringExtra("meta")

        if (etiqueta == null || etiqueta == "") {
            btnAgregar.setOnClickListener {

                if(sprCategoria.selectedItem.toString() == "Recordatorio")
                {
                    val recordatorio = Tabla_Recordatorios(etEtiqueta.text.toString(), etHora.text.toString())
                    lifeplanDatabase?.getTablaRecordatoriosDao()?.insertarRecordatorio(recordatorio)
                }
                else{
                    val actividad = Tabla_Actividades(etEtiqueta.text.toString(), etHora.text.toString() ,sprCategoria.selectedItem.toString(), sprMeta.selectedItem.toString())
                    lifeplanDatabase?.getTablaActividadesDao()?.insertarActividad(actividad)
                }


                var horaCompleta = etHora.text.toString()

                var separada =  horaCompleta.split(":")
                var parte2 = separada[1].split(" ")
                var horario = parte2[1]
                var pm = true

                if(horario == "PM")
                {
                    pm = false
                }

                var hora = separada[0].toInt()
                var minutos = parte2[0].toInt()

                establecerAlarma(etEtiqueta.text.toString(), hora, minutos, pm)
                finish()
            }
        } else {
            val id = intent.getIntExtra("id", 0)
            etEtiqueta.setText(etiqueta)
            etHora.setText(hora)
            btnAgregar.setOnClickListener {
                if(sprMeta.selectedItem.toString() == "Ninguna")
                {
                    val recordatorio = Tabla_Recordatorios(etEtiqueta.text.toString(), etHora.text.toString())
                    lifeplanDatabase?.getTablaRecordatoriosDao()?.actualizarRecordatorio(recordatorio)
                }
                else{
                    val actividad = Tabla_Actividades(etEtiqueta.text.toString(), etHora.text.toString() ,sprCategoria.selectedItem.toString(), sprMeta.selectedItem.toString())
                    lifeplanDatabase?.getTablaActividadesDao()?.actualizarActividad(actividad)
                }

                var horaCompleta = etHora.text.toString()

                var separada =  horaCompleta.split(":")
                var parte2 = separada[1].split(" ")
                var horario = parte2[1]
                var pm = false

                if(horario == "PM")
                {
                    pm = true
                }

                var hora = separada[0].toInt()
                var minutos = parte2[0].toInt()

                establecerAlarma(etEtiqueta.text.toString(), hora, minutos, pm)
                finish()
            }
        }
    }

    fun establecerAlarma(etiqueta: String, hora: Int, minutos: Int, pm: Boolean)
    {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            .putExtra(AlarmClock.EXTRA_MESSAGE, etiqueta)
            .putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_TIME)
            .putExtra(AlarmClock.EXTRA_HOUR, hora)
            .putExtra(AlarmClock.EXTRA_MINUTES, minutos)
            .putExtra(AlarmClock.EXTRA_IS_PM, pm)

        if(intent.resolveActivity(packageManager)!= null){
            Toast.makeText(this, "Actividad establecida.", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }




}

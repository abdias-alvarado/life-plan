package com.example.abdiasalvarado.lifeplan

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import java.util.*


class TimePicker: DialogFragment(), TimePickerDialog.OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendario = Calendar.getInstance()
        val hora = calendario.get(Calendar.HOUR_OF_DAY)
        val minutos = calendario.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hora, minutos, false)

    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the returned time
        val et: EditText = activity!!.findViewById(R.id.etHora) as EditText
        var horaCompleta: String
        var horaAMPM =  getHoraAMPM(hourOfDay)

        if(horaAMPM <= 9 && minute <= 9)
        {
            horaCompleta =  "0${getHoraAMPM(hourOfDay)}:0$minute ${getAMPM(hourOfDay)}"

        }
        else if(horaAMPM <= 9){
            horaCompleta =  "0${getHoraAMPM(hourOfDay)}:$minute ${getAMPM(hourOfDay)}"
        }
        else if(minute <= 9){
            horaCompleta =  "${getHoraAMPM(hourOfDay)}:0$minute ${getAMPM(hourOfDay)}"
        }
        else{
            horaCompleta =  "${getHoraAMPM(hourOfDay)}:$minute ${getAMPM(hourOfDay)}"
        }

        et.setText(horaCompleta)

    }


    // When user cancel the time picker dialog
    override fun onCancel(dialog: DialogInterface?) {
        Toast.makeText(activity,"Cancelado.", Toast.LENGTH_SHORT).show()
        super.onCancel(dialog)
    }


    // Custom method to get AM PM value from provided hour
    private fun getAMPM(hora: Int): String{
        return if(hora > 11) "PM" else "AM"
    }


    // Custom method to get hour for AM PM time format
    private fun getHoraAMPM(hora: Int) :Int
    {
        // Return the hour value for AM PM time format
        var horaAMPM = if (hora > 11) hora - 12 else hora
        if (horaAMPM == 0){horaAMPM = 12}
        return horaAMPM
    }

}
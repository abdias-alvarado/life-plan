package com.example.abdiasalvarado.lifeplan

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

class DatePick : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity,  this, anio, mes, dia)
    }

    override fun onDateSet(view: android.widget.DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {

    }

}
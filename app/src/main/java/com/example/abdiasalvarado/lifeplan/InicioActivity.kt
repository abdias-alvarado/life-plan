package com.example.abdiasalvarado.lifeplan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val fondo = object : Thread(){
            override fun run(){
                try {
                    Thread.sleep(7000)

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }
                catch (e: Exception)
                {
                    e.printStackTrace()
                }
            }

        }
        fondo.start()

    }
}

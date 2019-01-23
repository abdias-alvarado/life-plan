package com.example.abdiasalvarado.lifeplan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.AccessToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (AccessToken.getCurrentAccessToken() == null)
        {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        else
        {
            val intent = Intent(this, TableroPrincipal::class.java)
            startActivity(intent)
        }

    }
}

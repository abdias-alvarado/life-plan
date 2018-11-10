package com.example.abdiasalvarado.lifeplan

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*

class LoginActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        val intent= Intent(this, MainActivity::class.java)

        var btnLoginFB = findViewById<LoginButton>(R.id.fb_login_button)

        btnLoginFB.setOnClickListener {View.OnClickListener {
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult>{
                    override fun onSuccess(p0: LoginResult?) {
                        startActivity(intent)
                    }

                    override fun onCancel() {
                        Toast.makeText(applicationContext, "Cancelando Ingreso.", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(p0: FacebookException?) {
                        Toast.makeText(applicationContext, "Error de ingreso.", Toast.LENGTH_LONG).show()
                    }
                })

        }}






    }
}

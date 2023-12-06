package com.example.to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide() //Esconde a Action Bar

        //Looper que inicia o Main Activity apos 4 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val splash = Intent(this, MainActivity::class.java) // E atribuído a variavel splash o  Intent que iniciara a atividade Main Activity
            startActivity(splash) //Inicia a Activity de acordo com a variavel splash
        },4000) //daley de 4 segundos
    }
}
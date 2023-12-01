package com.example.calculatron

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView

class Final : AppCompatActivity() {

    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this)
        var aciertos=sharedPreferences.getInt("aciertos",0)
        var fallos=sharedPreferences.getInt("fallos",0)
        var txtaciertosant=findViewById<TextView>(R.id.acertadasesta)
        var txtfallosant=findViewById<TextView>(R.id.falladasesta)
        var textfallos=findViewById<TextView>(R.id.falladas)
        var txtaciertos=findViewById<TextView>(R.id.acertadas)
        var aciertosant=intent.getIntExtra("aciertosant",0)
        var fallosant=intent.getIntExtra("fallosant",0)
        txtaciertosant.text="Aciertos: $aciertosant"
        txtfallosant.text="Fallos: $fallosant"
        txtaciertos.text="Aciertos: $aciertos"
        textfallos.text="Fallos: $fallos"
    }
}
package com.example.calculatron

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
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
        var txtporcesta=findViewById<TextView>(R.id.porcentajeesta)
        var txtporc=findViewById<TextView>(R.id.porcentaje)
        var porcentaje=aciertos/fallos*10
        var aciertosant=intent.getIntExtra("aciertosant",0)
        var fallosant=intent.getIntExtra("fallosant",0)
        var porcentajeant=aciertosant/fallosant*10
        txtaciertosant.text="Aciertos: $aciertosant"
        txtfallosant.text="Fallos: $fallosant"
        txtaciertos.text="Aciertos: $aciertos"
        textfallos.text="Fallos: $fallos"
        txtporcesta.text="Porcentaje de aciertos: $porcentajeant"
        txtporc.text="Porcentaje de aciertos: $porcentaje"

    }

    fun reiniciar(view: View) {
        intent=Intent(this,Juego::class.java)
        startActivity(intent)
    }
}
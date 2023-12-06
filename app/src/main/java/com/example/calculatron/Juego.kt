package com.example.calculatron

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.isDigitsOnly
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class Juego : AppCompatActivity() {

    var numeroint=0
    var numero=""
    var respuesta=true
    var res=0
    var numero1=0
    var numero2=0
    var operaciones=0
    lateinit var resultadoant:TextView
    lateinit var resultado:TextView
    lateinit var resultadoprox:TextView
    lateinit var tutext:TextView
    lateinit var check:ImageView
    var acertadas=0
    var falladas=0
    var acertadasesta=0
    var falladasesta=0
    lateinit var aciertos:TextView
    var proxres=0
    var temporizador:CountDownTimer?=null
    lateinit var cuentatras:TextView
    lateinit var sharedPreferences:SharedPreferences
    lateinit var intent1 : Intent
    var durcntatras:Long=0
    var valmin=0
    var valmax=20
    var sumas=true
    var restas=true
    var multiplicacion=true
    var division=true
    var temppause=false
    var tmprestante:Long=0
    var ajustesact=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        resultadoant=findViewById(R.id.resultadoant)
        resultado=findViewById(R.id.resultado)
        resultadoprox=findViewById(R.id.resultadoprox)
        tutext=findViewById(R.id.tutext)
        check=findViewById(R.id.check)
        aciertos=findViewById(R.id.resumen)
        cuentatras=findViewById(R.id.cuentaAtras)
        var drcntras=findViewById<TextInputEditText>(R.id.durcntatrs)
        var vlmin=findViewById<TextInputEditText>(R.id.valmin)
        var vlmax=findViewById<TextInputEditText>(R.id.valmax)
        var checksumas=findViewById<CheckBox>(R.id.sumas)
        var checkrestas=findViewById<CheckBox>(R.id.restas)
        var checkmultiplicaciones=findViewById<CheckBox>(R.id.multiplicaciones)
        var checkdivisiones=findViewById<CheckBox>(R.id.divisiones)

        sharedPreferences= getDefaultSharedPreferences(this)
        durcntatras=sharedPreferences.getLong("cuentaatras",20000)
        valmin=sharedPreferences.getInt("valmin",0)
        valmax=sharedPreferences.getInt("valmax",20)
        sumas=sharedPreferences.getBoolean("sumas",true)
        restas=sharedPreferences.getBoolean("restas",true)
        multiplicacion=sharedPreferences.getBoolean("multiplicaciones",true)
        division=sharedPreferences.getBoolean("divisiones",true)




        drcntras.doAfterTextChanged {
            if (!drcntras.text.isNullOrBlank()) {
                if (!drcntras.text!!.isDigitsOnly()){
                    drcntras.error="Debes introducir un número entero"
                }else{
                    durcntatras=drcntras.text.toString().toLong()*1000
                }
            }
        }

        vlmin.doAfterTextChanged {
            if (!vlmin.text.isNullOrBlank()) {
                if (!vlmin.text!!.isDigitsOnly()){
                    vlmin.error="Debes introducir un número entero"
                }else{
                    val valorIngresado = vlmin.text.toString().toInt()

                    if (valorIngresado == 0) {
                        vlmin.error="Debe ser mayor de 0"
                    }else {
                        valmin = vlmin.text.toString().toInt()
                    }
                }
            }
        }

        vlmax.doAfterTextChanged {
            if (!vlmax.text.isNullOrBlank()) {
                if (!vlmax.text!!.isDigitsOnly()) {
                    vlmax.error = "Debes introducir un número entero"
                } else {
                    val valorIngresado = vlmax.text.toString().toInt()

                    if (valorIngresado == 0) {
                        vlmax.error = "Debe ser mayor de 0"
                    } else {
                        valmax = valorIngresado + 1
                    }
                }
            }
        }


        if (sumas){
            checksumas.isChecked=true
        }
        if (restas){
            checkrestas.isChecked=true
        }
        if (multiplicacion){
            checkmultiplicaciones.isChecked=true
        }
        if (division){
            checkdivisiones.isChecked=true
        }



        numero1 = Random.nextInt(from = valmin, until = valmax)
        numero2 = Random.nextInt(from = valmin, until = valmax)
        var aux=0
        if (numero1<numero2){
            aux=numero1
            numero1=numero2
            numero2=aux
        }
        while (res==0) {
            operaciones = Random.nextInt(3)
            if (operaciones == 0 && sumas) {
                resultado.text = "$numero1 + $numero2"
                res = numero1 + numero2
            } else if (operaciones == 1 && restas) {
                resultado.text = "$numero1 - $numero2"
                res = numero1 - numero2
            } else if (operaciones == 2 && multiplicacion) {
                resultado.text = "$numero1 * $numero2"
                res = numero1 * numero2
            } else if (operaciones == 3 && division && numero2==0) {
                resultado.text = "$numero1 / $numero2"
                res = numero1 / numero2
            }
        }
        operacion()
        empezarTemporizador(durcntatras)
        acertadas=sharedPreferences.getInt("aciertos",0)
        falladas=sharedPreferences.getInt("fallos",0)

    }

    fun pulsado(view: View) {
        if (!ajustesact) {
            when (view.id) {
                R.id.cero -> {
                    numero += "0"
                    tutext.text = numero
                }

                R.id.uno -> {
                    numero += "1"
                    tutext.text = numero
                }

                R.id.dos -> {
                    numero += "2"
                    tutext.text = numero
                }

                R.id.tres -> {
                    numero += "3"
                    tutext.text = numero
                }

                R.id.cuatro -> {
                    numero += "4"
                    tutext.text = numero
                }

                R.id.cinco -> {
                    numero += "5"
                    tutext.text = numero
                }

                R.id.seis -> {
                    numero += "6"
                    tutext.text = numero
                }

                R.id.siete -> {
                    numero += "7"
                    tutext.text = numero
                }

                R.id.ocho -> {
                    numero += "8"
                    tutext.text = numero
                }

                R.id.nueve -> {
                    numero += "9"
                    tutext.text = numero
                }

                R.id.ce -> {
                    numeroint = 0
                    numero = ""
                    tutext.text = numero
                }

                R.id.c -> {
                    if (!numero.equals("") || !numero.isNullOrBlank()) {
                        numero = numero.substring(0, numero.length - 1)
                        tutext.text = numero
                    }
                }

                R.id.igual -> {
                    if (numero != "" || !numero.isNullOrBlank()) {
                        numeroint = numero.toInt()
                        numero = ""
                        tutext.text = numero
                        respuesta = true
                        resultadoant.text = resultado.text.toString() + " = $res"
                        resultado.text = resultadoprox.text
                        animacion(resultado,0.9f,1.0f,100)
                        animacion(resultadoant,0.9f,1.0f,100)
                        animacion(resultadoprox,0.9f,1.0f,100)
                        if (numeroint == res) {
                            check.setImageResource(R.drawable.check)
                            acertadasesta++
                            aciertos.text = "Aciertos: $acertadasesta\nFallos: $falladasesta"
                            res = proxres
                        } else {
                            check.setImageResource(R.drawable.wrong)
                            falladasesta++
                            aciertos.text = "Aciertos: $acertadasesta\nFallos: $falladasesta"
                            res = proxres
                        }
                        operacion()
                    }
                }
            }
        }
    }

    fun operacion(){
        proxres=0
        if(respuesta) {
            respuesta=false
            numero1 = Random.nextInt(from = valmin, until = valmax)
            numero2 = Random.nextInt(from = valmin, until = valmax)
            var aux=0
            if (numero1<numero2){
                aux=numero1
                numero1=numero2
                numero2=aux
            }
            while (proxres==0) {
                operaciones = Random.nextInt(4)
                if (operaciones == 0 && sumas) {
                    proxres = numero1 + numero2
                    resultadoprox.text = "$numero1 + $numero2"
                } else if (operaciones == 1 && restas) {
                    proxres = numero1 - numero2
                    resultadoprox.text = "$numero1 - $numero2"
                } else if (operaciones == 2 && multiplicacion) {
                    proxres = numero1 * numero2
                    resultadoprox.text = "$numero1 * $numero2"
                } else if (operaciones == 3 && division && numero2==0) {
                    proxres = numero1 / numero2
                    resultadoprox.text = "$numero1 / $numero2"
                }
            }
            tutext.text=numero
        }
    }

    fun empezarTemporizador(tiempo:Long){
        temporizador=object:CountDownTimer(tiempo,1000){
            override fun onTick(millisUntilFinished: Long) {
                cuentatras.text = (millisUntilFinished / 1000).toString()
                tmprestante = millisUntilFinished
                if (tmprestante<=5000){
                    animacion(cuentatras,1.0f,0.9f,100)
                }
            }

            override fun onFinish() {
                if (!temppause) {
                    intent1 = Intent(applicationContext, Final::class.java)
                    acertadas += acertadasesta
                    falladas += falladasesta
                    sharedPreferences.edit().apply {
                        putInt("aciertos", acertadas)
                        putInt("fallos", falladas)
                        apply()
                    }
                    if (acertadasesta!=0 || falladasesta!=0) {
                        intent1.putExtra("aciertosant", acertadasesta)
                        intent1.putExtra("fallosant", falladasesta)
                    }
                    startActivity(intent1)
                }

            }
        }
        temporizador?.start()
    }

    fun ajustes(view: View) {
        var ajustes=findViewById<CardView>(R.id.panelajustes)
        animacion(view,0.9f,1.0f,100)
        if (ajustes.visibility==View.VISIBLE){
            ajustes.visibility = View.GONE
            empezarTemporizador(tmprestante)
            temppause=false
            ajustesact=false
        }else {
            animacion(ajustes,1.0f,0.95f,100)
            ajustes.visibility = View.VISIBLE
            ajustesact=true
            temppause=true
            temporizador!!.cancel()
        }
    }

    fun animacion(view: View, tamñoX:Float,tamñoY:Float,duracion:Long){
        view.animate().apply {
            scaleX(tamñoX)
            scaleY(tamñoX)
            duration=duracion
        }.withEndAction{
            view.animate().apply {
                scaleX(tamñoY)
                scaleY(tamñoY)
                duration=duracion
            }
        }
    }

    fun guardar(view: View) {
        sharedPreferences.edit().apply {
            putLong("cuentaatras",durcntatras)
            putInt("valmin",valmin)
            putInt("valmax",valmax)
            putBoolean("sumas",sumas)
            putBoolean("restas",restas)
            putBoolean("multiplicaciones",multiplicacion)
            putBoolean("divisiones",division)
            apply()
        }
        recreate()

    }

    fun check(view: View) {
        when(view.id){
            R.id.sumas ->{
                if (!sumas) {
                    sumas = true
                }else{
                    sumas=false
                }

            }
            R.id.restas ->{
                if (!restas) {
                    restas = true
                }else{
                    restas=false
                }

            }
            R.id.multiplicaciones ->{
                if (!multiplicacion) {
                    multiplicacion = true
                }else{
                    multiplicacion=false
                }

            }
            R.id.divisiones ->{
                if (!division) {
                    division = true
                }else{
                    division=false
                }

            }
        }

    }


}
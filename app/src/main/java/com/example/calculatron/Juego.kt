package com.example.calculatron

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.HandlerThread
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.isDigitsOnly
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import java.lang.NumberFormatException
import java.util.Random

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
    var durcntatras:Int=0
    var valmin=0
    var valmax=20
    var sumas=true
    var restas=true
    var multiplicacion=true
    var division=true
    var temppause=false
    var tmprestante:Long=0

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

        sharedPreferences= getDefaultSharedPreferences(this)
        durcntatras=sharedPreferences.getInt("cuentaatras",20000)

        var handler= Handler()

        drcntras.doAfterTextChanged {
            if (!drcntras.text.isNullOrBlank()) {
                if (!drcntras.text!!.isDigitsOnly()){
                    drcntras.error="Debes introducir un número entero"
                }else{
                    durcntatras=drcntras.text.toString().toInt()*1000
                }
            }
        }


        numero1 = Random().nextInt(100)
        numero2 = Random().nextInt(100)
        operaciones = Random().nextInt(3)
        if (operaciones == 0) {
            resultado.text="$numero1 + $numero2"
            res=numero1+numero2
        }else if(operaciones==1){
            resultado.text="$numero1 - $numero2"
            res=numero1-numero2
        }else if (operaciones==2){
            resultado.text="$numero1 * $numero2"
            res=numero1*numero2
        }else if (operaciones==3){
            resultado.text="$numero1 / $numero2"
            res=numero1/numero2
        }
        operacion()
        empezarTemporizador(durcntatras)
        acertadas=sharedPreferences.getInt("aciertos",0)
        falladas=sharedPreferences.getInt("fallos",0)
        println(falladas)
        println(falladasesta)
    }

    fun pulsado(view: View) {
        when(view.id){
            R.id.cero-> {
                numero += "0"
                tutext.text=numero
            }
            R.id.uno-> {
                numero += "1"
                tutext.text=numero
            }
            R.id.dos-> {
                numero += "2"
                tutext.text=numero
            }
            R.id.tres->{
                numero+="3"
                tutext.text=numero
            }
            R.id.cuatro-> {
                numero += "4"
                tutext.text=numero
            }
            R.id.cinco-> {
                numero += "5"
                tutext.text=numero
            }
            R.id.seis-> {
                numero += "6"
                tutext.text=numero
            }
            R.id.siete-> {
                numero += "7"
                tutext.text=numero
            }
            R.id.ocho-> {
                numero += "8"
                tutext.text=numero
            }
            R.id.nueve-> {
                numero += "9"
                tutext.text=numero
            }
            R.id.ce -> {
                numeroint = 0
                numero = ""
                tutext.text=numero
            }
            R.id.c->{
                numero=numero.substring(0,numero.length-1)
                tutext.text=numero

            }
            R.id.igual->{
                if (numero!="") {
                    numeroint = numero.toInt()
                    numero = ""
                    tutext.text = numero
                    respuesta = true
                    resultadoant.text = resultado.text.toString()+" = $res"
                    resultado.text = resultadoprox.text
                    if (numeroint==res){
                        check.setImageResource(R.drawable.check)
                        acertadasesta++
                        aciertos.text="Aciertos: $acertadasesta\nFallos: $falladasesta"
                        res=proxres
                    }else{
                        check.setImageResource(R.drawable.wrong)
                        falladasesta++
                        aciertos.text="Aciertos: $acertadasesta\nFallos: $falladasesta"
                        res=proxres
                    }
                    operacion()
                }
            }
        }
    }

    fun operacion(){
        if(respuesta) {
            respuesta=false
            numero1 = Random().nextInt(100)
            numero2 = Random().nextInt(100)
            var aux=0
            if (numero1<numero2){
                aux=numero1
                numero1=numero2
                numero2=aux
            }
            operaciones = Random().nextInt(4)
            if (operaciones == 0) {
                proxres=numero1+numero2
                resultadoprox.text="$numero1 + $numero2"
            }else if(operaciones==1){
                proxres=numero1-numero2
                resultadoprox.text="$numero1 - $numero2"
            }else if (operaciones==2){
                proxres=numero1*numero2
                resultadoprox.text="$numero1 * $numero2"
            }else if (operaciones==3){
                proxres=numero1/numero2
                resultadoprox.text="$numero1 / $numero2"
            }
            tutext.text=numero
        }
    }

    fun empezarTemporizador(tiempo:Int){
        var duracion:Long=tiempo.toString().toLong()
        temporizador=object:CountDownTimer(duracion,1000){
            override fun onTick(millisUntilFinished: Long) {
                cuentatras.text = (millisUntilFinished / 1000).toString()
                tmprestante = millisUntilFinished
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
                    intent1.putExtra("aciertosant", acertadasesta)
                    intent1.putExtra("fallosant", falladasesta)
                    startActivity(intent1)
                }

            }
        }
        temporizador?.start()
    }

    fun ajustes(view: View) {
        var ajustes=findViewById<CardView>(R.id.panelajustes)
        if (ajustes.visibility==View.VISIBLE){
            ajustes.visibility = View.GONE
            empezarTemporizador(tmprestante.toString().toInt())
            temppause=false
        }else {
            ajustes.visibility = View.VISIBLE
            temppause=true
            temporizador!!.cancel()
        }
    }

    fun guardar(view: View) {
        sharedPreferences.edit().apply {
            putInt("cuentaatras",durcntatras)
            apply()
        }
        recreate()
    }


}
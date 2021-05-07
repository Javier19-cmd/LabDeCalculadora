package com.example.labdecalcu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labdecalcu.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Variables globales.
    private var num1 = 0.0 //Variable num1, esta variable almacena el primer número ingresado en la calculadora antes de presionar el botón para operar el número con otro
    private var num2 = 0.0 //Variable num2, esta variable almacena el segundo número ingresado en la calculadora después de presionar el botón para operar con el número de antes.
    private var operacion = 0 //Se crea una variable operación que será la encargada de llamar a las operaciones que se harán con la calculadora.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultadoText.text = "0"
        operacion = SIN_OPERACION

        unoBtn.setOnClickListener { numberPressed("1") } //Se hacer referencia al botón de 1, y a la vez se escribe el número 1 en pantalla.
        dosBtn.setOnClickListener { numberPressed("2") } //Se hacer referencia al botón de 2, y a la vez se escribe el número 2 en pantalla.
        tresBtn.setOnClickListener { numberPressed("3") } //Se hacer referencia al botón de 3, y a la vez se escribe el número 3 en pantalla.
        cuatroBtn.setOnClickListener { numberPressed("4") } //Se hacer referencia al botón de 4, y a la vez se escribe el número 4 en pantalla.
        cincoBtn.setOnClickListener { numberPressed("5") } //Se hacer referencia al botón de 5, y a la vez se escribe el número 5 en pantalla.
        seisBtn.setOnClickListener { numberPressed("6") } //Se hacer referencia al botón de 6, y a la vez se escribe el número 6 en pantalla.
        sieteBtn.setOnClickListener { numberPressed("7") } //Se hacer referencia al botón de 7, y a la vez se escribe el número 7 en pantalla.
        ochoBtn.setOnClickListener { numberPressed("8") }  //Se hacer referencia al botón de 8, y a la vez se escribe el número 8 en pantalla.
        nueveBtn.setOnClickListener { numberPressed("9") } //Se hacer referencia al botón de 9, y a la vez se escribe el número 9 en pantalla.
        ceroBtn.setOnClickListener { numberPressed("0") }  //Se hacer referencia al botón de 0, y a la vez se escribe el número 0 en pantalla.
        puntoBtn.setOnClickListener { numberPressed(".") } //Se hacer referencia al botón del punto, y a la vez se escribe el punto en pantalla.

        clearBtn.setOnClickListener { resetAll() } //Se hace referencia al botón que limpia todo el texto de la pantalla y también se llama al método resetAll para limpiar a las variables y al texto en pantalla.

        sumaBtn.setOnClickListener { operationPressed(SUMA) } //Se hace referencia al botón de sumar y a la vez se hace referencia a la operación SUMA que se hace aquí dentro.
        restaBtn.setOnClickListener { operationPressed(RESTA) } //Se hace referencia al botón de restar y a la vez se hace referencia a la operación RESTA que se hace aquí dentro.
        multiplicarBtn.setOnClickListener { operationPressed(MULTIPLICACION) } //Se hace referencia al botón de multiplicación y a la vez se hace referencia a la operación MULTIPLICACION que se hace aquí dentro.
        divisionBtn.setOnClickListener { operationPressed(DIVISION) } //Se hace referencia al botón de división y a la vez se hace referencia a la operación DIVISIÓN que se hace aquí dentro.


        igualBtn.setOnClickListener { resolvePressed() } //Se hace referencia al botón de igual y a la vez se resuelve la operación que se haya solicitado hacer.
    }

    //Método encargado de almacenar los números en variables.
    private fun numberPressed(num: String){

        //Si en caso el texto es igual a 0, o sea como se enseña en pantalla, y el número a ingresar posee un punto, entonces se escribe como decimal.
        if(resultadoText.text == "0" && num != ".") {
            resultadoText.text = "$num"
        } else {
            //Si en caso el número no es decimal, entonces se escribe normal.
            resultadoText.text = "${resultadoText.text}$num"
        }

        //Si en caso la operación está "vacía", entonces se almacena el número en la variable num1.
        if(operacion == SIN_OPERACION){
            num1 = resultadoText.text.toString().toDouble()
        } else {
            //Si ya se almacenó alguna operación en la variable operación, entonces se procede a almacenar el segundo número en la variable num2.
            num2 = resultadoText.text.toString().toDouble()
        }
    }

    //Método encargado de almacenar la operación deseada en una variable.
    private fun operationPressed(operacion: Int){
        this.operacion = operacion //Se procede a almacenar la operación en una variable
        num1 = resultadoText.text.toString().toDouble() //Se almacena el primer número en la variable num1.

        resultadoText.text = "0"
    }

    //Método encargado de resolver la operación.
    private fun resolvePressed(){

        //Se crea una variable result que contiene un ciclo when.
        val result = when(operacion) {
            //En este ciclo when están todas las operaciones que puede realizar la calculadora.
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            else -> 0 //Si en caso no hay una operación a realizar, entonces se devuelve un cero.
        }

        num1 = result as Double //En la variable num1 se almacena el resultado como un número doble.

        resultadoText.text = if("$result".endsWith(".0")) {
            //Si en caso el resultado contiene el .0, entonces se elimina eso del texto para que no haya texto inecesario.
             "$result".replace(".0","")
            } else {
            //Si en caso sí tiene decimal, entonces se le agrega un flotante para que sea más exacto el resultado.
                "%.2f".format(result)
            }
    }

    //Método resetAll: Este método será el encargado de limpiar tanto el texto como las variables para que no haya resultados erróneos en la calculadora.
    private fun resetAll(){
        resultadoText.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    //En esta parte se tienen todoas las operaciones que podrá hacer la calculadora de este laboratorio.
    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val SIN_OPERACION = 0
    }
}
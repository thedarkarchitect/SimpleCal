package com.example.simplecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    // to check the last input
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        //View returns the button clicked
//        Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text)//beacuse view is a button we need to convert it and then get the text
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")//this adds the dot to the input
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
            }
        }
    }

    fun onEqual(view: View){

        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)//this returns everything after the index entered

                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    //separate the string values entered
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    //separate the string values entered
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    //separate the string values entered
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    //separate the string values entered
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }

            }catch (e : java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String): String{
        var value = result

        if(result.contains(".0")){
            value = result.substring(0, result.length-2)//helps element the float after point
        }
        return value
    }

    private fun isOperatorAdded(value : String) : Boolean {
        return if(value.startsWith("-")){//this will deal with negative entries by ignoring them
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}
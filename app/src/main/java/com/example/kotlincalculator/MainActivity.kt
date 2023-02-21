package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textViewInput: TextView? = null

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewInput = findViewById(R.id.textViewInputId)
    }

    // Function that executes when we click on the button
    fun onDigit(view: View){

        // Since the button has the text property
        // Casting View as Button, and get its text
        textViewInput?.append((view as Button).text)


        // When we press on a Digit last Numeric should be True
        lastNumeric = true
        lastDot = false
    }

    // Function for the clear button
    fun onClear(view: View){

        textViewInput?.setText("")
    }

    // Decimal Point
    fun onDecimalPoint(view: View){

        // If lastNumeric is True and doesnt have dot at last
        if (lastNumeric && !lastDot){
            textViewInput?.append(".")
            lastNumeric = false
            lastDot = true
        }

    }

    fun onOperator(view : View){

        // Safe wrap
        textViewInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){ // if there is a operator dont add it followed by
                textViewInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

    fun onEqual(view: View){

        // Checking if the last number is numeric
        if(lastNumeric){
            var textViewInputValue = textViewInput?.text.toString()
            var prefix = ""

            try{

                if (textViewInputValue.startsWith("-")){
                    prefix = "-"

                    // Ignore the -
                    textViewInputValue = textViewInputValue.substring(1)
                }

                if (textViewInputValue.contains("-")){


                    val splitValue = textViewInputValue.split("-")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    // If prefix is not empty then add it to the one
                    // Add the - sign at the end after the calculation
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textViewInput?.text = removeDecimalAtEnd((one.toDouble() - two.toDouble()).toString())

                }
                else if (textViewInputValue.contains("+")){


                    val splitValue = textViewInputValue.split("+")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    // If prefix is not empty then add it to the one
                    // Add the - sign at the end after the calculation
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textViewInput?.text = removeDecimalAtEnd((one.toDouble() + two.toDouble()).toString())

                }
                else if (textViewInputValue.contains("*")){


                    val splitValue = textViewInputValue.split("*")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    // If prefix is not empty then add it to the one
                    // Add the - sign at the end after the calculation
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textViewInput?.text = removeDecimalAtEnd((one.toDouble() * two.toDouble()).toString())

                }

                else if (textViewInputValue.contains("/")){


                    val splitValue = textViewInputValue.split("/")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    // If prefix is not empty then add it to the one
                    // Add the - sign at the end after the calculation
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textViewInput?.text = removeDecimalAtEnd((one.toDouble() / two.toDouble()).toString())


                }

            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeDecimalAtEnd(value: String) : String{
            var result = value
        if (value.contains(".0"))
            result = result.substring(0 , result.length - 2)

        return result
    }




    private fun isOperatorAdded(value : String ): Boolean {

        // First one is to ignore the - at front
        return if (value.startsWith("-")){
            false
        }else{

            // Check for the signs, if found returns True
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}
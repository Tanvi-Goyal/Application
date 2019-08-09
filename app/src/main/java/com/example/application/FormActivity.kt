package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_charts_main.*
import kotlinx.android.synthetic.main.activity_form.*
import org.w3c.dom.Text
import java.lang.StringBuilder
import java.util.ArrayList

class FormActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        val list = ArrayList<MainActivity.FieldM>()
        list.add(MainActivity.FieldM(0, "First Name", "first name", "textview"))
        list.add(MainActivity.FieldM(1, "Last Name", "last name", "textview"))
        list.add(MainActivity.FieldM(2, "Address", "address", "textview"))
        list.add(MainActivity.FieldM(3, "Phone Number", "contact number", "textview_numeric"))
        list.add(MainActivity.FieldM(4, "Email", "email id", "textview_email"))
        list.add(MainActivity.FieldM(5, "Email", "Enter Email ID", "edittext"))



        for (item in list) {
            val type = item.type
            when (type) {
                "textview" -> {
                    val textView = TextView(this)
                    textView.text = item.title
                    textView.id = item.id

                    main_layout.addView(textView)
                }
                "textview_numeric" -> {
                    val textView = TextView(this)
                    textView.text = item.title
                    textView.inputType = InputType.TYPE_CLASS_NUMBER
                    textView.id = item.id

                    main_layout.addView(textView)
                }
                "textview_email" -> {
                    val textView = TextView(this)
                    textView.text = item.title
                    textView.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    textView.id = item.id

                    main_layout.addView(textView)
                }

                "edittext" -> {
                    val editText = EditText(this)
                    editText.hint = item.hint
                    editText.id = item.id

                    main_layout.addView(editText)
                }

                else -> { // Note the block
                    Log.w("TAG" , "4")
                }
            }

        }


        submit_btn.setOnClickListener{
            val result = StringBuilder()
            for(item in list) {

                val type = item.type

                when (type) {
                    "textview" -> {
                        val text:TextView = main_layout.findViewById<TextView>(item.id)
                        result.append(text.text)
                        result.append(System.getProperty("line.separator"));
                    }
                    "textview_numeric" -> {
                        val text:TextView = main_layout.findViewById<TextView>(item.id)
                        result.append(text.text)
                        result.append(System.getProperty("line.separator"));
                    }
                    "textview_email" -> {
                        val text:TextView = main_layout.findViewById<TextView>(item.id)
                        Toast.makeText(this, text.text, Toast.LENGTH_SHORT).show()
                        result.append(text.text)
                    }

                    "edittext" -> {
                        val edit:EditText = main_layout.findViewById<EditText>(item.id)
                        Toast.makeText(this, edit.getText(), Toast.LENGTH_SHORT).show()
                        result.append(edit.text)
                    }

                    else -> { // Note the block
                        Log.w("TAG" , "4")
                    }
                }

                Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()

            }

        }

    }
}



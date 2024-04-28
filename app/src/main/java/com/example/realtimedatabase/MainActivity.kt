package com.example.realtimedatabase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.internal.api.FirebaseNoSignedInUserException

class MainActivity : AppCompatActivity() {

    lateinit var edtext:EditText
    lateinit var button:Button
    lateinit var text:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtext = findViewById(R.id.edtvalue)
        button = findViewById(R.id.btnsubmit)
        text = findViewById(R.id.textView)




        button.setOnClickListener {

            retrive()

        }


    }
    fun retrive(){

        var fire = FirebaseDatabase.getInstance()
        var refrence = fire.getReference("message")

        if(edtext != null){
            var name = edtext.text.toString()

            refrence.setValue(name)
        }else{
            Toast.makeText(this , "enter value", Toast.LENGTH_SHORT).show()
        }


        refrence.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var value = snapshot.getValue(String::class.java)
                text.text = value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("message", "${error}")

            }

        })


    }
}
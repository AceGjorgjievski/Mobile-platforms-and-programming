package com.example.lab1mpip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lab1mpip.viewmodel.ShowTextViewModel

class ExplicitActivity : AppCompatActivity() {

    private lateinit var btnSubmit: Button;
    private lateinit var btnCancel: Button;
    private lateinit var editTextChoice: TextView;

    private lateinit var textViewModel : ShowTextViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)


        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        editTextChoice = findViewById(R.id.editTextChoice);

        textViewModel = ViewModelProvider(this)[ShowTextViewModel::class.java]

        textViewModel.getUserChoiceValue().observe(this) {
            editTextChoice.setText(textViewModel.getUserChoice())
        }


        btnSubmit.setOnClickListener { v ->
            Intent().let { i ->
                i.putExtra("userChoice", this.editTextChoice.text.toString())
                setResult(RESULT_OK, i)
                finish()
            }
        }

        btnCancel.setOnClickListener { v ->
            Intent().let { i ->
                editTextChoice.text = "";
                setResult(RESULT_CANCELED, i)
                finish()
            }
        }


    }
}
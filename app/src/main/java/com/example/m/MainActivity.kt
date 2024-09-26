package com.example.m

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextGrade: EditText
    private lateinit var buttonRandom: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextGrade = findViewById(R.id.editTextGrade)
        buttonRandom = findViewById(R.id.buttonRandom)

        registerForContextMenu(editTextGrade)


        buttonRandom.setOnClickListener {
            val randomNumber = (1..50).random()
            editTextGrade.setText(randomNumber.toString())
            updateBackgroundColor(randomNumber)
        }


        editTextGrade.setOnFocusChangeListener { _, _ ->
            val grade = editTextGrade.text.toString().toIntOrNull()
            if (grade != null) {
                updateBackgroundColor(grade)
            }
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_color_quality -> {
                val grade = editTextGrade.text.toString().toIntOrNull()
                if (grade != null) {
                    updateBackgroundColor(grade)
                } else {
                    Toast.makeText(this, "Введите оценку!", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.menu_exit -> {
                finish()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun updateBackgroundColor(grade: Int) {
        when (grade) {
            1 -> editTextGrade.setBackgroundColor(Color.parseColor("#FFA500"))
            2 -> editTextGrade.setBackgroundColor(Color.parseColor("#FFFF00"))
            3 -> editTextGrade.setBackgroundColor(Color.parseColor("#008000"))
            4 -> editTextGrade.setBackgroundColor(Color.parseColor("#0000FF"))
            5 -> editTextGrade.setBackgroundColor(Color.parseColor("#FF0000"))
            in 1..10 -> editTextGrade.setBackgroundColor(Color.parseColor("#FF0000"))
            in 11..20 -> editTextGrade.setBackgroundColor(Color.parseColor("#FFA500"))
            in 21..30 -> editTextGrade.setBackgroundColor(Color.parseColor("#FFFF00"))
            in 31..40 -> editTextGrade.setBackgroundColor(Color.parseColor("#008000"))
            in 41..50 -> editTextGrade.setBackgroundColor(Color.parseColor("#0000FF"))
            else -> editTextGrade.setBackgroundColor(Color.TRANSPARENT)
        }
    }
}

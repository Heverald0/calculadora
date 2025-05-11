package com.example.calculadora

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private var mr = 0.0
    private var temp1 = 0.0
    private var temp2 = 0.0
    private var operacao = 0
    private var result = 0.0
    private var isResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val display = findViewById<TextView>(R.id.display)

        configurarNumeros(display)
        configurarMemoria(display)
        configurarFuncoesCientificas(display)
        configurarOperacoes(display)
        configurarResultado(display)
    }
}
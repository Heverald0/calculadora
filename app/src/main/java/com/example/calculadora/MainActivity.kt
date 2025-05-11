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

     private fun configurarNumeros(display: TextView) {
        val numeros = mapOf(
            R.id.btnZero to "0", R.id.btnUm to "1", R.id.btnDois to "2",
            R.id.btnTres to "3", R.id.btnQuatro to "4", R.id.btnCinco to "5",
            R.id.btnSeis to "6", R.id.btnSete to "7", R.id.btnOito to "8", R.id.btnNove to "9"
        )

        numeros.forEach { (id, valor) ->
            findViewById<TextView>(id).setOnClickListener {
                display.text = if (isResult || display.text.toString() == "0") valor
                else display.text.toString() + valor
                isResult = false
            }
        }

        findViewById<TextView>(R.id.btnVirgula).setOnClickListener {
            if (!display.text.contains(".")) {
                display.append(".")
            }
        }

        findViewById<TextView>(R.id.btnCE).setOnClickListener {
            display.text = "0"
            temp1 = 0.0
            temp2 = 0.0
            result = 0.0
            operacao = 0
            isResult = false
        }
    }

    private fun configurarMemoria(display: TextView) {
        findViewById<TextView>(R.id.btnMR).setOnClickListener {
            display.text = mr.toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnMmais).setOnClickListener {
            mr += display.text.toString().toDoubleOrNull() ?: 0.0
            display.text = "0"
            isResult = true
        }

        findViewById<TextView>(R.id.btnMmenos).setOnClickListener {
            mr -= display.text.toString().toDoubleOrNull() ?: 0.0
            display.text = "0"
            isResult = true
        }
    }

     private fun configurarFuncoesCientificas(display: TextView) {
        val getValue = { display.text.toString().toDoubleOrNull() ?: 0.0 }

        findViewById<TextView>(R.id.btnSen).setOnClickListener {
            display.text = sin(Math.toRadians(getValue())).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnCos).setOnClickListener {
            display.text = cos(Math.toRadians(getValue())).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnTan).setOnClickListener {
            display.text = tan(Math.toRadians(getValue())).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnSqrt).setOnClickListener {
            display.text = sqrt(getValue()).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnQuadrado).setOnClickListener {
            val valor = getValue()
            display.text = (valor * valor).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnPotencia).setOnClickListener {
            temp1 = getValue()
            operacao = 5
            display.text = "0"
            isResult = false
        }

        findViewById<TextView>(R.id.btnFatorial).setOnClickListener {
            val n = display.text.toString().toIntOrNull() ?: 0
            display.text = if (n < 0) "Erro" else (1..n).fold(1L) { acc, i -> acc * i }.toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnLn).setOnClickListener {
            val valor = getValue().coerceAtLeast(1e-10) // evitar ln(0)
            display.text = ln(valor).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnLog).setOnClickListener {
            val valor = getValue().coerceAtLeast(1e-10)
            display.text = log10(valor).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnE).setOnClickListener {
            display.text = E.toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnPi).setOnClickListener {
            display.text = PI.toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnSinal).setOnClickListener {
            display.text = (-getValue()).toString()
            isResult = true
        }

        findViewById<TextView>(R.id.btnInverso).setOnClickListener {
            val valor = getValue()
            display.text = if (valor != 0.0) (1 / valor).toString() else "Erro"
            isResult = true
        }
    }

    private fun configurarOperacoes(display: TextView) {
        fun setOperacao(op: Int) {
            temp1 = display.text.toString().toDoubleOrNull() ?: 0.0
            operacao = op
            display.text = "0"
            isResult = false
        }

        findViewById<TextView>(R.id.btnMais).setOnClickListener { setOperacao(1) }
        findViewById<TextView>(R.id.btnMenos).setOnClickListener { setOperacao(2) }
        findViewById<TextView>(R.id.btnVezes).setOnClickListener { setOperacao(3) }
        findViewById<TextView>(R.id.btnDividir).setOnClickListener { setOperacao(4) }
    }

}

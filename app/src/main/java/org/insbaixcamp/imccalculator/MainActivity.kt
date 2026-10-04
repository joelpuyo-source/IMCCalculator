package org.insbaixcamp.imccalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var nom: String = ""
    private var pes: Int = 80
    private var altura: Int = 170

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btMas: Button
        var btMenos: Button
        var tvPeso: TextView
        var btCalcular: Button
        var tvAltura: TextView
        var sbAltura: SeekBar
        var tvResult: TextView
        var etNom: EditText
        var tvNameResult: TextView
        var cvResultat: CardView


        btMas = findViewById(R.id.bt_mas)
        btMenos = findViewById(R.id.bt_menos)
        tvPeso = findViewById(R.id.tv_pes)
        sbAltura = findViewById(R.id.sb_altura)
        tvResult= findViewById(R.id.tv_result_imc)
        btCalcular= findViewById(R.id.bt_calcular)
        etNom= findViewById(R.id.et_nom)
        tvAltura = findViewById(R.id.tv_altura_num)
        tvNameResult = findViewById(R.id.tv_name_result)
        cvResultat = findViewById(R.id.cv_resultat)

        btMas.setOnClickListener (){
            pes++
            tvPeso.text = pes.toString()
        }

        btMenos.setOnClickListener (){
            pes--
            tvPeso.text = pes.toString()
        }

        sbAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean){
                altura = progress
                tvAltura.text = altura.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        btCalcular.setOnClickListener (){
            nom = etNom.text.toString()
            val alturaMetros = altura / 100f
            val imc = if (alturaMetros > 0f) pes / (alturaMetros * alturaMetros) else 0f
            cvResultat.visibility = View.VISIBLE
            tvNameResult.text = "$nom el teu IMC es de"
            tvResult.text = String.format(Locale.getDefault(), "%.2f", imc)
        }

    }

}
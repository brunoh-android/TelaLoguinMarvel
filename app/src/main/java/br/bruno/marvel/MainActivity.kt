package br.bruno.marvel

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.bruno.marvel.databinding.ActivityMainBinding
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validaSenha()


        binding.txtsenha.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                validaSenha()
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.button.setOnClickListener {
            if (validaSenha()){
                    val intent = Intent(this, ListaDeAliados::class.java)
                    startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Senha incorreta",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun validaSenha(): Boolean {

        val senhaTxt = binding.txtsenha.text.toString()
        val lowerCase = Pattern.compile("[a-z]")
        val upperCase = Pattern.compile("[A-Z]")
        val digiCase = Pattern.compile("[0-9]")
        val especiCase = Pattern.compile("[@#|=\$%^&+=]")

        if (!lowerCase.matcher(senhaTxt).find()) {
            binding.az.setTextColor(Color.RED)
            return false
        } else {
            binding.az.setTextColor(Color.GREEN)
        }
        if (!upperCase.matcher(senhaTxt).find()) {
            binding.AZ.setTextColor(Color.RED)
            return false
        } else {
            binding.AZ.setTextColor(Color.GREEN)
        }
        if (!digiCase.matcher(senhaTxt).find()) {
            binding.numeros.setTextColor(Color.RED)
            return false
        } else {
            binding.numeros.setTextColor(Color.GREEN)
        }
        if (!especiCase.matcher(senhaTxt).find()) {
            binding.caracterEspecial.setTextColor(Color.RED)
            return false
        } else {
            binding.caracterEspecial.setTextColor(Color.GREEN)
        }
        if (senhaTxt.length < 8) {
            binding.caracter.setTextColor(Color.RED)
            return false
        } else {
            binding.caracter.setTextColor(Color.GREEN)
        }
        return true
    }
}



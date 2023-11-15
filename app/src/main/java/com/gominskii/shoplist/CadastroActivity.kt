package com.gominskii.shoplist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import com.gominskii.shoplist.databinding.ActivityCadastroBinding
import com.google.android.material.snackbar.Snackbar

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)

        binding.textView.text = intent.getStringExtra("nome")

        setContentView(binding.root)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)

        super.onBackPressed()
    }
}
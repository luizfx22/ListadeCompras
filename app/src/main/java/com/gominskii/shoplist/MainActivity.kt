package com.gominskii.shoplist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.gominskii.shoplist.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.newListFab.setOnClickListener {
            val intent = Intent(this@MainActivity, CadastroActivity::class.java)
            intent.putExtra("nome", "CARAJO")

            startActivity(intent)
        }
    }
}
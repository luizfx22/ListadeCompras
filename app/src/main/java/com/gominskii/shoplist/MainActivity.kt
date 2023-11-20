package com.gominskii.shoplist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import com.gominskii.shoplist.R.layout.home_lista_item
import com.gominskii.shoplist.adapters.HomeListaAdapter
import com.gominskii.shoplist.databinding.ActivityMainBinding
import com.gominskii.shoplist.view_models.HomeListaVM

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var listaDeAfazeres: ArrayList<HomeListaVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.newListFab.setOnClickListener {
            val intent = Intent(this@MainActivity, CadastroActivity::class.java)
            startActivity(intent)
        }

        listaDeAfazeres = ArrayList()

        // Add sample data
        listaDeAfazeres.add(HomeListaVM(1, "Locais para visitar"))
        listaDeAfazeres.add(HomeListaVM(2, "Partes do computador"))
        listaDeAfazeres.add(HomeListaVM(3, "Festa do João"))

        val adapter = HomeListaAdapter(this, home_lista_item, listaDeAfazeres)
        binding.listasListView.adapter = adapter

        setContentView(binding.root)
    }
}
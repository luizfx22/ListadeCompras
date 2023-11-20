package com.gominskii.shoplist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gominskii.shoplist.adapters.ListaAdapter
import com.gominskii.shoplist.databinding.ActivityListaViewBinding
import com.gominskii.shoplist.view_models.ListaVM

class ListaView : AppCompatActivity() {
    private lateinit var binding: ActivityListaViewBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var listaDeAfazeres: ArrayList<ListaVM>
    private lateinit var recyclerViewAdapter: ListaAdapter

    private var listaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaViewBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Change toolbar color
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.purple_500))

        // Enable back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = binding.listaViewRecycler
        listaDeAfazeres = ArrayList()

        recyclerViewAdapter = ListaAdapter(listaDeAfazeres, false)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add sample data
        val hashMap = HashMap<Int, HashMap<String, Any>>()

        val lista1 = HashMap<String, Any>()
        lista1["nome"] = "Locais para visitar"
        lista1["items"] = listOf(
            ListaVM("Praia", false),
            ListaVM("Parque", true),
            ListaVM("Museu", false),
            ListaVM("Cinema", false),
            ListaVM("Teatro", false),
        )

        hashMap[1] = lista1

        val lista2 = HashMap<String, Any>()
        lista2["nome"] = "Partes do computador"
        lista2["items"] = listOf(
            ListaVM("Placa mãe", true),
            ListaVM("Processador", true),
            ListaVM("Memória", true),
            ListaVM("GPU", false),
            ListaVM("Fonte", true),
            ListaVM("SSD", false),
        )

        hashMap[2] = lista2

        val lista3 = HashMap<String, Any>()
        lista3["nome"] = "Festa do João"
        lista3["items"] = listOf(
            ListaVM("Bolo", true),
            ListaVM("Salgadinho", true),
            ListaVM("Refrigerante", true),
            ListaVM("Docinhos", true),
            ListaVM("Carvão", true),
        )

        hashMap[3] = lista3

        // Get list from intent id
        val listaId = intent.getIntExtra("id", 0)

        val lista = hashMap[listaId]

        if (lista != null) {
            supportActionBar?.title = lista["nome"].toString()
            val items = lista["items"] as List<ListaVM>
            listaDeAfazeres.addAll(items)
        }

        binding.editListFab.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("id", listaId)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    override fun onBackPressed() {
        goHome()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
package com.gominskii.shoplist

import android.content.Intent
import android.os.Bundle
import android.util.ArrayMap
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaViewBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Change toolbar title
        supportActionBar?.title = intent.getStringExtra("nomeLista")

        // Change toolbar color
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.purple_500))

        // Enable back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = binding.listaViewRecycler
        listaDeAfazeres = ArrayList()

        recyclerViewAdapter = ListaAdapter(listaDeAfazeres, true)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add sample data
        var lists = ArrayMap<Int, ListaVM>()
        lists[1] = ListaVM("Arroz", true)
        lists[1] = ListaVM("Feijão", true)
        lists[1] = ListaVM("Macarrão", false)
        lists[1] = ListaVM("Carne", false)
        lists[1] = ListaVM("Frango", false)

        lists[2] = ListaVM("Carvão", true)
        lists[2] = ListaVM("Carne", true)
        lists[2] = ListaVM("Cerveja", false)
        lists[2] = ListaVM("Refrigerante", false)

        lists[3] = ListaVM("Bolo", true)
        lists[3] = ListaVM("Salgadinho", true)
        lists[3] = ListaVM("Refrigerante", false)
        lists[3] = ListaVM("Suco", false)

        // Get list from intent id
        val id = intent.getIntExtra("id", 0)
        val list = lists[id]

        // Add items to list
        for (item in lists) {
            if (item.key == id) {
                this.recyclerViewAdapter.adicionarItem(
                    item.value
                )
            }
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
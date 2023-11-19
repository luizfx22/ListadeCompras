package com.gominskii.shoplist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gominskii.shoplist.adapters.HomeListaAdapter
import com.gominskii.shoplist.databinding.ActivityMainBinding
import com.gominskii.shoplist.view_models.HomeListaVM
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var listaDeAfazeres: ArrayList<HomeListaVM>
    private lateinit var recyclerViewAdapter: HomeListaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.newListFab.setOnClickListener {
            val intent = Intent(this@MainActivity, CadastroActivity::class.java)
            startActivity(intent)
        }

        this.recyclerView = this.binding.listasRecyclerView
        this.listaDeAfazeres = ArrayList()

        this.recyclerViewAdapter = HomeListaAdapter(this.listaDeAfazeres)

        this.recyclerView.adapter = this.recyclerViewAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        // Add sample data
        this.addItem(HomeListaVM(1, "Lista de compras do mês"))
        this.addItem(HomeListaVM(2, "Lista de compras para o churrasco"))
        this.addItem(HomeListaVM(3, "Lista de compras para a festa do João"))

        this.recyclerViewAdapter.setOnItemClickListener(object :
            HomeListaAdapter.OnItemClickListener {
            override fun onItemClick(item: HomeListaVM) {
                val intent = Intent(this@MainActivity, ListaView::class.java)
                intent.putExtra("id", item.id)
                startActivity(intent)
            }
        })

        setContentView(binding.root)
    }

    private fun addItem(item: HomeListaVM): Boolean {
        try {
            this.recyclerViewAdapter.adicionarItem(
                item
            )

            this.recyclerViewAdapter.notifyDataSetChanged()
            this.recyclerView?.refreshDrawableState()

            return true

            //
        } catch (e: Exception) {
            Snackbar.make(
                this.binding.root,
                "Erro ao adicionar item: ${e.message}",
                Snackbar.LENGTH_SHORT
            ).show()

            return false
        }
    }
}
package com.gominskii.shoplist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gominskii.shoplist.adapters.ListaAdapter
import com.gominskii.shoplist.databinding.ActivityCadastroBinding
import com.gominskii.shoplist.view_models.ListaVM
import com.google.android.material.snackbar.Snackbar

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var listaDeAfazeres: ArrayList<ListaVM>
    private lateinit var recyclerViewAdapter: ListaAdapter

    private var listaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityCadastroBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Change toolbar color
        this.supportActionBar?.setBackgroundDrawable(getDrawable(R.color.purple_500))

        // Enable back button
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get recycler view
        this.recyclerView = this.binding.listItemsRecyclerView
        this.listaDeAfazeres = ArrayList()

        // Set adapter
        this.recyclerViewAdapter = ListaAdapter(this.listaDeAfazeres, true)
        this.recyclerView.adapter = this.recyclerViewAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        listaId = intent.getIntExtra("id", -1)

        // Check if intent has extra int "id"
        if (listaId != -1) {
            this.supportActionBar?.title = "Editar lista"

            // Add sample data
            val hashMap = HashMap<Int, HashMap<String, Any>>()

            val lista1 = HashMap<String, Any>()
            lista1["nome"] = "Locais para visitar"
            lista1["items"] = listOf(
                ListaVM("Praia", false),
                ListaVM("Parque", false),
                ListaVM("Museu", false),
                ListaVM("Cinema", false),
                ListaVM("Teatro", false),
            )

            hashMap[1] = lista1

            val lista2 = HashMap<String, Any>()
            lista2["nome"] = "Partes do computador"
            lista2["items"] = listOf(
                ListaVM("Placa mãe", false),
                ListaVM("Processador", false),
                ListaVM("Memória", false),
                ListaVM("GPU", false),
                ListaVM("Fonte", false),
                ListaVM("SSD", false),
            )

            hashMap[2] = lista2

            val lista3 = HashMap<String, Any>()
            lista3["nome"] = "Festa do João"
            lista3["items"] = listOf(
                ListaVM("Bolo", false),
                ListaVM("Salgadinho", false),
                ListaVM("Refrigerante", false),
                ListaVM("Docinhos", false),
                ListaVM("Carvão", false),
            )

            hashMap[3] = lista3

            // Get list from intent id
            val lista = hashMap[listaId]

            if (lista != null) {
                this.binding.listNameInput.setText(lista["nome"].toString())
                val items = lista["items"] as List<ListaVM>
                this.listaDeAfazeres.addAll(items)
            }

            //
        } else
            this.supportActionBar?.title = "Nova lista"

        // On click addItemButton add item to list
        this.binding.addItemButton.setOnClickListener {
            if (this.binding.listContentItemInput.text.isNotEmpty()) {
                this.addItem()

                //
            } else {
                Snackbar.make(it, "Digite um item para adicionar!", Snackbar.LENGTH_SHORT).show()
            }
        }

        // Handle save button
        this.binding.saveListFab.setOnClickListener {
            if (this.binding.listNameInput.text.isNotEmpty()) {
                this.goBack(listaId)

                Snackbar.make(it, "Lista salva com sucesso!", Snackbar.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            Snackbar.make(it, "Digite um nome para a lista!", Snackbar.LENGTH_SHORT).show()
            return@setOnClickListener
        }

        setContentView(this.binding.root)
    }

    private fun addItem(): Boolean {
        try {
            this.recyclerViewAdapter.adicionarItem(
                ListaVM(
                    this.binding.listContentItemInput.text.toString(),
                    false
                )
            )

            this.recyclerViewAdapter.notifyDataSetChanged()
            this.recyclerView?.refreshDrawableState()

            this.binding.listContentItemInput.text.clear()

            return true

            //
        } catch (e: Exception) {
            Snackbar.make(
                this.binding.addItemButton,
                "Erro ao adicionar item: ${e.message}",
                Snackbar.LENGTH_SHORT
            ).show()

            return false
        }
    }

    override fun onBackPressed() {
        this.goBack(listaId)
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        this.onBackPressed()
        return true
    }

    private fun goBack(id: Int = -1) {
        if (id != -1) {
            val intent = Intent(this, ListaView::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

            return
        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
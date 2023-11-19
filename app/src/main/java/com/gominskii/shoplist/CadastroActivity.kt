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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityCadastroBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Change toolbar title
        this.supportActionBar?.title = "Nova lista"

        // Change toolbar color
        this.supportActionBar?.setBackgroundDrawable(getDrawable(R.color.purple_500))

        // Enable back button
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.recyclerView = this.binding.listItemsRecyclerView
        this.listaDeAfazeres = ArrayList()

        this.recyclerViewAdapter = ListaAdapter(this.listaDeAfazeres, true)
        this.recyclerView.adapter = this.recyclerViewAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)


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
                this.goHome()
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
        this.goHome()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        this.onBackPressed()
        return true
    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
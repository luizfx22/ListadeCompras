package com.gominskii.shoplist.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.gominskii.shoplist.ListaView
import com.gominskii.shoplist.R
import com.gominskii.shoplist.view_models.HomeListaVM

class HomeListaAdapter(context: Context, resource: Int, objects: ArrayList<HomeListaVM>) :
    ArrayAdapter<HomeListaVM>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView =
                LayoutInflater.from(context).inflate(R.layout.home_lista_item, parent, false)
        }

        val currentItem = getItem(position)

        val textView = listItemView?.findViewById<TextView>(R.id.listasListViewItemTextView)

        textView?.text = currentItem?.name

        listItemView?.setOnClickListener {
            currentItem?.let {
                Log.d("CustomAdapter", "Clicou em: ${it.id}")
                val intent = Intent(context, ListaView::class.java)
                intent.putExtra("id", it.id)
                context.startActivity(intent)
            }
        }

        return listItemView!!
    }
}

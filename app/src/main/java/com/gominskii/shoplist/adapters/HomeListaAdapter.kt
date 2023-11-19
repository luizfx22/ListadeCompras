package com.gominskii.shoplist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gominskii.shoplist.R
import com.gominskii.shoplist.view_models.HomeListaVM

class HomeListaAdapter(
    private val listaAfazeres: ArrayList<HomeListaVM>
) : RecyclerView.Adapter<HomeListaAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: HomeListaVM)
    }

    private var clickListener: OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemTextView: TextView = itemView.findViewById(R.id.itemTextView)

        fun bind(item: HomeListaVM) {
            itemTextView.text = item.texto
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val afazeresView = inflater.inflate(R.layout.home_lista_item, parent, false)

        return ViewHolder(afazeresView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaAfazeres[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            clickListener?.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return listaAfazeres.size
    }

    fun adicionarItem(item: HomeListaVM) {
        listaAfazeres.add(item)
        notifyItemInserted(listaAfazeres.size - 1)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }
}
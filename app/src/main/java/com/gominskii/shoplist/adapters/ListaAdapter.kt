package com.gominskii.shoplist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gominskii.shoplist.R
import com.gominskii.shoplist.view_models.ListaVM

class ListaAdapter(
    private val listaAfazeres: ArrayList<ListaVM>,
    private val defaultCheckboxDisabled: Boolean = false,
    private val checkOnTextClick: Boolean = true
) : RecyclerView.Adapter<ListaAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val concluidoCheckBox: CheckBox = itemView.findViewById(R.id.concluidoCheckBox)
        private val itemTextView: TextView = itemView.findViewById(R.id.itemTextView)

        fun bind(item: ListaVM) {
            concluidoCheckBox.isEnabled = !defaultCheckboxDisabled

            if (checkOnTextClick) {
                itemTextView.setOnClickListener {
                    if (concluidoCheckBox.isEnabled)
                        concluidoCheckBox.isChecked = !concluidoCheckBox.isChecked
                }
            }

            if (defaultCheckboxDisabled) concluidoCheckBox.isChecked = defaultCheckboxDisabled

            itemTextView.text = item.texto

            if (!defaultCheckboxDisabled) {
                concluidoCheckBox.isChecked = item.concluido

                if (item.concluido) {
                    itemTextView.paintFlags =
                        itemTextView.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    itemTextView.paintFlags =
                        itemTextView.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }

            concluidoCheckBox.setOnCheckedChangeListener { _, isChecked ->
                item.concluido = isChecked

                // Strike through text
                if (isChecked) {
                    itemTextView.paintFlags =
                        itemTextView.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    itemTextView.paintFlags =
                        itemTextView.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val afazeresView = inflater.inflate(R.layout.lista_item, parent, false)

        return ViewHolder(afazeresView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaAfazeres[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listaAfazeres.size
    }

    fun adicionarItem(item: ListaVM) {
        listaAfazeres.add(item)
        notifyItemInserted(listaAfazeres.size - 1)
    }
}
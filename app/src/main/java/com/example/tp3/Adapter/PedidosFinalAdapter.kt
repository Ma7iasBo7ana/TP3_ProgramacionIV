package com.example.tp3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.Model.Pedidos
import com.example.tp3.Model.PedidosFinal
import com.example.tp3.R

class PedidosFinalAdapter (private val dataSet: ArrayList<PedidosFinal>): RecyclerView.Adapter<PedidosFinalAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val vendido: TextView
        val cajero: TextView
        val repartidor: TextView


        init {
            vendido = view.findViewById(R.id.lf_vendido)
            cajero = view.findViewById(R.id.lf_cajero)
            repartidor = view.findViewById(R.id.lf_repartidor)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.listado_final,parent,false )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vendido=dataSet[position].vendido
        val cajero=dataSet[position].cajero
        val repartidor=dataSet[position].repartidor
        holder.vendido.text="$"+vendido
        holder.cajero.text= cajero
        holder.repartidor.text=repartidor



    }


}
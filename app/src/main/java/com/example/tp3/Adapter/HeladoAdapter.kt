package com.example.tp3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.Model.Helado
import com.example.tp3.R

class HeladoAdapter (private val dataSet: ArrayList<Helado>): RecyclerView.Adapter<HeladoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val precio: TextView
        val foto: ImageView
        val gustos: TextView

        init {
            precio = view.findViewById(R.id.lh_precio)
            foto = view.findViewById(R.id.lh_foto)
            gustos = view.findViewById(R.id.lh_gustos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.listado_helado,parent,false )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val precio=dataSet[position].precio.toString()
        val gustos=dataSet[position].gustos
        holder.precio.text="PRECIO"+precio
        holder.gustos.text="Gustos:"+ gustos

    }
}

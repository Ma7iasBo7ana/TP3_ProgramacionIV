package com.example.tp3

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.Adapter.HeladoAdapter
import com.example.tp3.Adapter.PedidosAdapter
import com.example.tp3.Adapter.PedidosFinalAdapter
import com.example.tp3.Model.*


class MainActivity : AppCompatActivity() {
    lateinit var rvHelado:RecyclerView
    lateinit var rb_helado:RadioGroup
    lateinit var rb_option:RadioButton
    lateinit var layoutMostrar:LinearLayout
    lateinit var bVerHelado:Button
    lateinit var bcomprar:Button
    lateinit var agregar:Button
    lateinit var helado:Helado
    var listaHelado:ArrayList<Helado> = ArrayList<Helado>()
    lateinit var spinnercajas:Spinner
    lateinit var spinnersabor:Spinner
    lateinit var spiner12:Spinner
    lateinit var spiner13:Spinner
    lateinit var spiner14:Spinner
    lateinit var cantidadpedidos:TextView
    var contadorCaja1: Int =0
    var contadorCaja2: Int =0
    var contadorCaja3: Int =0
    var listacajas= mutableListOf("Caja1","Caja2","Caja3")
    var caja1:Boolean=true
    var caja2:Boolean=true
    var caja3:Boolean=true
    lateinit var listapedido:ListView
    lateinit var verpedidos:Button
    lateinit var listaPedidos:ArrayList<Pedidos>
    lateinit var Pedido:Pedidos
    var listarepartidor= mutableListOf("Repartidor1","Repartidor2","Repartidor3","Repartidor4")
    lateinit var srepartidor:Spinner
    lateinit var listaPedidosFinal:ArrayList<PedidosFinal>
    lateinit var PedidoFinal:PedidosFinal





    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicialElementos()
        agregar.isEnabled=false
        verpedidos.isEnabled=false
        spinnersabor.setEnabled(false)
        spiner12.setEnabled(false)
        spiner13.setEnabled(false)
        spiner14.setEnabled(false)





        verpedidos.setOnClickListener(View.OnClickListener {
            rvHelado.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
            rvHelado.adapter=PedidosFinalAdapter(listaPedidosFinal)

        })


        spinnercajas.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                if (caja1){
                    if (listacajas[position]=="Caja1")
                        cantidadpedidos.text=contadorCaja1.toString()
                }
                if (caja2){
                    if (listacajas[position]=="Caja2")
                        cantidadpedidos.text=contadorCaja2.toString()
                }

               if (caja3){
                   if (listacajas[position]=="Caja3")
                       cantidadpedidos.text=contadorCaja3.toString()
               }


            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {

            }
        })


        bVerHelado.setOnClickListener(View.OnClickListener {
            rb_option=findViewById(rb_helado.checkedRadioButtonId)
            spinnersabor.setEnabled(true)
            spiner12.setEnabled(true)
            spiner13.setEnabled(true)
            spiner14.setEnabled(true)
            agregar.isEnabled=(true)


            var view=LayoutInflater.from(this).inflate(R.layout.mostrar_helado_layout,null)

            val foto:ImageView=view.findViewById(R.id.mh_foto)
            val descripcion:TextView=view.findViewById(R.id.mh_descripcion)



            when(rb_option.text.toString().toLowerCase()){
                "cono"->{
                    foto.setImageResource(R.mipmap.cono)
                    descripcion.setText("Cono de dos gustos\nPrecio:$100")
                    spiner13.setEnabled(false)
                    spiner14.setEnabled(false)
                }
                "kilo"->{
                    foto.setImageResource(R.mipmap.kilo)
                    descripcion.setText("Kilo de 4 gustos\nPrecio:$700")
                    spiner13.setEnabled(true)
                    spiner14.setEnabled(true)

                }
                "cuarto"->{
                    foto.setImageResource(R.mipmap.uncuarto)
                    descripcion.setText("Cuarto de 3 gustos\nPrecio:$400")
                    spiner13.setEnabled(true)
                    spiner14.setEnabled(false)

                }

            }
            agregar.setOnClickListener(View.OnClickListener {
                bcomprar.isEnabled=true
                when(rb_option.text.toString().toLowerCase()){
                    "cono"->{
                        helado=Cono(arrayListOf(spinnersabor.getSelectedItem().toString(),spiner12.getSelectedItem().toString()),precio = 100.0)
                        Pedido=Pedidos("Cono",helado.precio.toString(),R.mipmap.cono)

                    }
                    "kilo"->{

                        helado=Kilo(arrayListOf(spinnersabor.getSelectedItem().toString(),spiner12.getSelectedItem().toString(),spiner13.getSelectedItem().toString(),spiner14.getSelectedItem().toString()),precio = 700.0)
                        Pedido=Pedidos("Kilo",helado.precio.toString(),R.mipmap.kilo)

                    }
                    "cuarto"->{
                        helado=Cuarto(arrayListOf(spinnersabor.getSelectedItem().toString(),spiner12.getSelectedItem().toString(),spiner13.getSelectedItem().toString()),precio = 400.0)
                        Pedido=Pedidos("Cuarto",helado.precio.toString(),R.mipmap.uncuarto)

                    }
                }

                listaHelado.add(helado)
                listaPedidos.add(Pedido)
                rvHelado.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
                rvHelado.adapter=PedidosAdapter(listaPedidos)

                Toast.makeText(this, "Agregado al carrito",Toast.LENGTH_LONG).show()
            })

            layoutMostrar.removeAllViews()
            layoutMostrar.addView(view)


        })
        bcomprar.setOnClickListener(View.OnClickListener {
            layoutMostrar.removeAllViews()

            if (spinnercajas.getSelectedItem()=="Caja1")
            {
                contadorCaja1++
                cantidadpedidos.text=contadorCaja1.toString()

            }
            if (spinnercajas.getSelectedItem()=="Caja2")
            {
                contadorCaja2++
                cantidadpedidos.text=contadorCaja2.toString()

            }
            if (spinnercajas.getSelectedItem()=="Caja3")
            {
                contadorCaja3++
                cantidadpedidos.text=contadorCaja3.toString()

            }
            if (contadorCaja1==5)
            {
                listacajas.remove("Caja1")
                spinertipo(listacajas)
                Toast.makeText(this,"La caja 1 llego al limite de pedidos",Toast.LENGTH_LONG).show()
                val builder= AlertDialog.Builder(it.context)
                builder.setTitle("Limite de pedidos")
                builder.setMessage("La caja 1 llego al limite de pedidos diarios, se sacara del listado")
                val alerta:AlertDialog=builder.create()
                alerta.show()
                caja1=false
                contadorCaja1++
            }
            if (contadorCaja2>=10)
            {
                listacajas.remove("Caja2")
                spinertipo(listacajas)
                val builder= AlertDialog.Builder(it.context)
                builder.setTitle("Limite de pedidos")
                builder.setMessage("La caja 2 llego al limite de pedidos diarios, se sacara del listado")
                val alerta:AlertDialog=builder.create()
                alerta.show()
            }
            if (contadorCaja3>=15)
            {
                listacajas.remove("Caja3")
                spinertipo(listacajas)
                val builder= AlertDialog.Builder(it.context)
                builder.setTitle("Limite de pedidos")
                builder.setMessage("La caja 3 llego al limite de pedidos diarios, se sacara del listado")
                val alerta:AlertDialog=builder.create()
                alerta.show()
            }
            var dinero:Double=0.0

            listaPedidos.forEach { item ->
                dinero+=item.precio.toDouble()
            }
            PedidoFinal= PedidosFinal(dinero.toString(),spinnercajas.getSelectedItem().toString(),srepartidor.getSelectedItem().toString())
            listaPedidosFinal.add(PedidoFinal)

            listaPedidos = ArrayList<Pedidos>()
            rvHelado.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
            rvHelado.adapter=PedidosAdapter(listaPedidos)

            val builder=AlertDialog.Builder(it.context)
            builder.setTitle("Compra")
            builder.setMessage("El total a pagar es de:"+(dinero))
            builder.setIcon(android.R.drawable.ic_dialog_info)


            builder.setPositiveButton("Aceptar"){DialogInterface, i ->
                Toast.makeText(this,"Compra Finalizada",Toast.LENGTH_LONG).show()
            }

            val alerta:AlertDialog=builder.create()
            alerta.show()
            verpedidos.isEnabled=true
            bcomprar.isEnabled=false
            agregar.isEnabled=false
            spinnersabor.setEnabled(false)
            spiner12.setEnabled(false)
            spiner13.setEnabled(false)
            spiner14.setEnabled(false)
        })

    }


    private  fun inicialElementos(){
        rb_helado=findViewById(R.id.rg_TipoHelado)
        layoutMostrar=findViewById(R.id.ly_pedidos)
        bVerHelado=findViewById(R.id.b_ver)
        bcomprar=findViewById(R.id.b_comprar)
        spinnercajas=findViewById(R.id.s_empleados)
        spinnersabor=findViewById(R.id.spinner10)
        cantidadpedidos=findViewById(R.id.t_cantpedidos)
        spiner12=findViewById(R.id.spinner12)
        spiner13=findViewById(R.id.spinner13)
        spiner14=findViewById(R.id.spinner14)
        agregar=findViewById(R.id.mh_acept)
        verpedidos=findViewById(R.id.b_pedidos)
        rvHelado=findViewById(R.id.RecycleHelado)
        listaPedidos = ArrayList<Pedidos>()
        srepartidor=findViewById(R.id.s_repartidor)
        listaPedidosFinal = ArrayList<PedidosFinal>()
        spinertipo(listacajas)
        spinerrepartidor(listarepartidor)
        spinersabores()
    }

    fun spinertipo(listacajas: MutableList<String>)
    {

        var adaptador= ArrayAdapter(this,android.R.layout.simple_spinner_item,listacajas)
        spinnercajas.adapter=adaptador
    }
    fun spinerrepartidor(repartidores: MutableList<String>)
    {

        var adaptador= ArrayAdapter(this,android.R.layout.simple_spinner_item,repartidores)
        srepartidor.adapter=adaptador
    }

    fun spinersabores()
    {
        var listasabores= arrayOf("Chocolate","Dulce de leche","Vainilla","Frutilla")
        var adaptador= ArrayAdapter(this,android.R.layout.simple_spinner_item,listasabores)
        spinnersabor.adapter=adaptador
        spiner12.adapter=adaptador
        spiner13.adapter=adaptador
        spiner14.adapter=adaptador

    }



}
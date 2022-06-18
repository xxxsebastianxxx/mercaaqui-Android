package com.example.mercaaqui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercaaqui.R
import org.json.JSONObject


class facturasAdapter(private val facturaslist: ArrayList<JSONObject>, private val facturasListener: FacturasListener):RecyclerView.Adapter<facturasAdapter.ViewHolder>() {


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var producto : TextView = view.findViewById(R.id.nombre_producto)
        var cantidad : TextView = view.findViewById(R.id.cantidad_producto)
        var precio_unitario : TextView = view.findViewById(R.id.precio_unitario_producto)
        var precio_total : TextView = view.findViewById(R.id.precio_total_producto)
        var num_factura : TextView = view.findViewById(R.id.id_factura)
        var itemImg :ImageView = view.findViewById(R.id.item_image)

        fun bind(facturas: JSONObject){
            Log.d("menAdapter", facturaslist.toString())
            producto.text = facturas.getString("Nombre_producto")
            cantidad.text = facturas.getString("Cantidad")
            precio_unitario.text = facturas.getString("Precio_total")
            precio_total.text = facturas.getString("Precio")
            num_factura.text = facturas.getString("id")
           // itemImg=facturas.get("id")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_item_factura, parent, false)

    )

    override fun getItemCount() = this.facturaslist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val factura = facturaslist[position]
        Log.d("menAdapter2", facturaslist.toString())
        try {
            Glide.with(holder.itemView.context)
                .load(factura.getString("nombre"))
                .into(holder.itemImg)
            holder.bind(factura)

            holder.itemView.setOnClickListener {
                facturasListener.onFacturasListenerClicked(factura, position)
            }
        }catch (e: Exception){
            Log.w("imagenProducto", "no cargo la imagen")
        }
    }
}
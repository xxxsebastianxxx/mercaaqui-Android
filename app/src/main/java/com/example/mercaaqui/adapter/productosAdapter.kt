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


class productosAdapter(private val productoslist: ArrayList<JSONObject>, private val productosListener: ProductosListener):RecyclerView.Adapter<productosAdapter.ViewHolder>() {


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nombre: TextView = view.findViewById(R.id.txtNombreproducto)
        var imagen : ImageView = view.findViewById(R.id.imagenProducto)
        var precio : TextView = view.findViewById(R.id.txtprecio)

        fun bind(productos: JSONObject){
            Log.d("menAdapter", productoslist.toString())
            nombre.text = productos.getString("Nombre_producto")
            precio.text = productos.getString("Precio")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_item_producto, parent, false)

    )

    override fun getItemCount() = this.productoslist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val producto = productoslist[position]
        Log.d("menAdapter2", productoslist.toString())
   try {
            Glide.with(holder.itemView.context)
                .load(producto.getString("Imagen"))
                .into(holder.imagen)
            holder.bind(producto)

            holder.itemView.setOnClickListener {
                productosListener.onProductosListenerClicked(producto, position)
            }
        }catch (e: Exception){
            Log.w("imagenProducto", "no cargo la imagen")
        }
    }


}
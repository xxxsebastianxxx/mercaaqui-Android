package com.example.mercaaqui.productos

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.bumptech.glide.Glide
import com.example.mercaaqui.R

import org.json.JSONObject


class DetalleProductosFragment : DialogFragment() {

    private lateinit var tbProductDets: Toolbar
    private lateinit var imgDetalleProduct: ImageView
    private lateinit var NombreDetalleProduct: TextView
    private lateinit var DescDetalleProduct: TextView
    private lateinit var PrecioDetalleProduct: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullscreamDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_detalle_productos, container, false)
        this.tbProductDets = ll.findViewById(R.id.tbProductDets)

        this.imgDetalleProduct = ll.findViewById(R.id.imgDetalleProduct)
        this.NombreDetalleProduct = ll.findViewById(R.id.NombreDetalleProduct)
        this.DescDetalleProduct = ll.findViewById(R.id.DescDetalleProduct)
        this.PrecioDetalleProduct = ll.findViewById(R.id.PrecioDetalleProduct)
        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.tbProductDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_close_24)
        this.tbProductDets.setNavigationOnClickListener{
            dismiss()
        }



        val productos = JSONObject(arguments?.getString("productos"))

        this.tbProductDets.title = productos.getString("Nombre_producto")
        this.NombreDetalleProduct.text = productos.getString("Nombre_producto")
        this.DescDetalleProduct.text = productos.getString("Descripcion")
        this.PrecioDetalleProduct.text = productos.getString("Precio")


        Glide.with(this)
            .load(productos.getString("Imagen"))
            .into(this.imgDetalleProduct)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }



}
package com.example.mercaaqui.adapter

import com.example.mercaaqui.model.productos
import org.json.JSONObject


interface ProductosListener {
    fun onProductosListenerClicked(productos: JSONObject, position: Int)
}
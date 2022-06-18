package com.example.mercaaqui.adapter

import org.json.JSONObject


interface ProductosListener {
    fun onProductosListenerClicked(productos: JSONObject, position: Int)
}
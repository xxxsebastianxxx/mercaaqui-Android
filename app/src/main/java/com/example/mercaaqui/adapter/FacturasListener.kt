package com.example.mercaaqui.adapter

import org.json.JSONObject


interface FacturasListener {
    fun onFacturasListenerClicked(facturas: JSONObject, position: Int)
}
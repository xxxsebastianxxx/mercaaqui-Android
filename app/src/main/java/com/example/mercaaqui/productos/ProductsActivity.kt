package com.example.mercaaqui.productos

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mercaaqui.R
import org.json.JSONArray
import org.json.JSONObject


class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        val url = "http://192.168.1.100/mercaaqui/listaProductoAll.php"
        val queue = Volley.newRequestQueue(this)
        val textnombre = findViewById<TextView>(R.id.txtNombreproducto)
        val imgprod = findViewById<ImageView>(R.id.imagenProducto)
        val priceprod = findViewById<TextView>(R.id.precio)
        Log.d( "onCreate", "Ingreso")
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            val jsonArray = JSONArray(response)
            Log.d("men", url)
            for (i in 0 until jsonArray.length()){
                val jsonObject = JSONObject(jsonArray.getString(i))

                priceprod.text = jsonObject.get("Precio").toString().toInt().toString()
                textnombre.text = jsonObject.get("Nombre_producto").toString()
                Glide.with(this)
                    .load(jsonObject.get("Imagen")
                        .apply{(RequestOptions.circleCropTransform())}
                        .toString()).into(imgprod)
            }
        }, Response.ErrorListener { error ->
            Log.w( "errorL", error)
        })
        queue.add(stringRequest)
    }
}
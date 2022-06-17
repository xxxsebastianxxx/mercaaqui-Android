package com.example.mercaaqui.productos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.mercaaqui.adapter.ProductosListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.mercaaqui.R
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mercaaqui.adapter.productosAdapter
import com.example.mercaaqui.model.productos
import kotlinx.android.synthetic.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.collections.ArrayList

class ListaProductoFragment : Fragment(), ProductosListener {
        private  lateinit var  recycler: RecyclerView
        private lateinit var viewAlpha:View
        private  lateinit var pgbar: ProgressBar
        private lateinit var rlproductos: RelativeLayout
        private  lateinit var listaproductos: ArrayList<JSONObject>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_lista_producto,container,false)
        //listaproductos=ArrayList<JSONObject>()
        val url = "http://192.168.1.104/mercaaqui/listaProductoAll.php"
        val queue = Volley.newRequestQueue(this.context)
        Log.d("Mensaje","ingreso a class")

            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
               val jsonArray = JSONArray(response)
                this.listaproductos=ArrayList()
           try {
               var i = 0
               val l = jsonArray.length()
               Log.d("men2",l.toString())
               while (i < l){
                   listaproductos.add(jsonArray[i] as JSONObject)
                   i++
                   Log.d("mensaje", "ingreso")
               }
               Log.d("men", listaproductos.toString())
               this.recycler.adapter = productosAdapter( this.listaproductos, this )
               this.viewAlpha.visibility = View.INVISIBLE
               this.pgbar.visibility = View.INVISIBLE

           }catch (e: JSONException){
                Log.w("error", e)
           }
    },{ error ->
        Log.w("jsonError", error)
    })
    queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.RecyclerViewDeProductos)
        this.viewAlpha = ll.findViewById(R.id.view_productos)
        this.pgbar = ll.findViewById(R.id.prgs_productos)
        this.rlproductos = ll.findViewById(R.id.rlproductos)

        Log.d("Mensaje","ingreso a rv")
        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)


    }

    override fun onProductosListenerClicked(productos: JSONObject, position: Int) {
        val bundle = bundleOf("productos" to productos.toString())
        findNavController().navigate(
            R.id.detalleProductosFragment,
            bundle
        )
    }
}
package com.example.mercaaqui.facturas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mercaaqui.R
import com.example.mercaaqui.adapter.FacturasListener
import com.example.mercaaqui.adapter.facturasAdapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ListaFacturaFragment : Fragment(), FacturasListener {
    private  lateinit var  recycler: RecyclerView
    private lateinit var viewAlpha: View
    private  lateinit var pgbar: ProgressBar
    private lateinit var rlfacturas: RelativeLayout
    private  lateinit var listafacturas: ArrayList<JSONObject>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_lista_factura,container,false)
        //listaproductos=ArrayList<JSONObject>()
        val url = "https://mercaaqui.ga/facturasAll"
        val queue = Volley.newRequestQueue(this.context)
        Log.d("Mensaje","ingreso a class")

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.listafacturas=ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                Log.d("men2",l.toString())
                while (i < l){
                    listafacturas.add(jsonArray[i] as JSONObject)
                    i++
                    Log.d("mensaje", "ingreso")
                }
                Log.d("men", listafacturas.toString())
                this.recycler.adapter = facturasAdapter( this.listafacturas, this )
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE

            }catch (e: JSONException){
                Log.w("error", e)
            }
        },{ error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rl_facturas)
        this.viewAlpha = ll.findViewById(R.id.view_facturas)
        this.pgbar = ll.findViewById(R.id.prgs_facturas)
        this.rlfacturas = ll.findViewById(R.id.rlFacturas)

        Log.d("Mensaje","ingreso a rv")
        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onFacturasListenerClicked(facturas: JSONObject, position: Int) {
        /**val bundle = bundleOf("facturas" to facturas.toString())
        findNavController().navigate(
        R.id.detalleProductosFragment,
        bundle
        )**/
    }
}
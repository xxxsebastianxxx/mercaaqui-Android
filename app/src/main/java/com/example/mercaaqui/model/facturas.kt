package com.example.mercaaqui.model

import java.io.Serializable

class facturas: Serializable {
    var imagen : String= ""
    var producto : String= ""
    var cantidad : Int = 0
    var precio_unitario : Int = 0
    var precio_total : Int = 0
    var num_factura : Int = 0
}

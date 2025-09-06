package org.example

object GestorPedidos {

    fun calcularSubtotal(productos: List<Producto>): Double{
        return productos.sumOf {it.calcularPrecioFinal()}
    }

    fun calcularDescuentoCliente(subtotal: Double, tipoCliente: String): Double{
        var descuento: Double = 0.0
        when(tipoCliente.lowercase()){
            "regular" -> descuento = subtotal * 0.05
            "vip" -> descuento = subtotal * 0.10
            "premium" -> descuento = subtotal * 0.15
            else -> throw IllegalArgumentException("Tipo de cliente no valido.")
        }
        return descuento
    }

    fun calcularIva(subtotal: Double): Double{
        return subtotal * 0.19
    }

    fun calcularTotal(subtotal: Double, descuento: Double, iva: Double): Double{
        return subtotal - descuento + iva
    }

}
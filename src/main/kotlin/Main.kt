package org.example

import kotlinx.coroutines.runBlocking

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() = runBlocking {

    //Aca se define el catalogo que se va a mostrar
    var catalogo = listOf(ProductoComida("Hamburguesa", 5000.0, 100, false),
        ProductoComida("Caviar", 10000.0, 500, true),
        ProductoBebida("Coca-cola", 1500.0, 50, "pequena"),
        ProductoBebida("Jugo natural",3000.0, 300, "grande"))

    println("--- SISTEMA ACOPLE'S FOOD---")
    //aca mostramos el catalogo que hicimos
    println("Catalogo Disponible:")
    catalogo.forEachIndexed { index, producto ->
        println("${index + 1}. $producto") }

    //aca el usuario ingresa los productos que desea comprar
    println("\nSeleccione productos(numeros separados por coma):")
    val seleccion = readln().split(",").mapNotNull{it.trim().toIntOrNull()?.takeIf { idx -> idx in 1..catalogo.size }}

    //aca creamos un map para alamcenar los productos tomados en seleccion
    val productosSeleccionados = seleccion.map {catalogo[it-1]}

    if(productosSeleccionados.isEmpty()) {
        println("No se seleccionaron productos validos. Saliendo...")
        return@runBlocking
    }


    //A continuacion se presenta la opcion para que el usuario elija el tipo de cliente
    println("\nIndique el tipo de cliente(regular/vip/premium):")
    val tipoCliente = readln().lowercase()

    //ahora simulamos el procesamiento asincornico
    println("\nProcesando pedido...")
    var estado: EstadoPedido = EstadoPedido.EnPreparacion
    println("Estado: En Preparación")
    //delay(3000)
    estado = EstadoPedido.Listo

    //Procedemos a calcular los totales
    val subtotal = GestorPedidos.calcularSubtotal(productosSeleccionados)
    val descuento = GestorPedidos.calcularDescuentoCliente(subtotal, tipoCliente)
    val iva = GestorPedidos.calcularIva(subtotal)
    val total = GestorPedidos.calcularTotal(subtotal,descuento,iva)


    //Por ultimo se muestra el resumen de la compra
    println("\n*** RESUMEN DEL PEDIDO ***")
    productosSeleccionados.forEach{
        println("- ${it}")
    }

    println("Subtotal: \$${"%,.0f".format(subtotal)}")
    println("Descuento de Cliente:$tipoCliente (${((descuento/subtotal) * 100).toInt()}%) : -\$${"%,.0f".format(descuento)}")
    println("IVA (19%): \$${"%,.0f".format(iva)}")
    println("TOTAL: \$${"%,.0f".format(total)}")

    println("\nEstado FInal: ${(estado as EstadoPedido.Listo)::class.simpleName}")

}
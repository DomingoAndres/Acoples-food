package org.example

open class Producto(var nombre:String, var precio:Double, var categoria:String, var tiempoDePreparacion:Int) {


    init{
        require(precio > 0){"El precio debe ser mayor a 0"}
        require(tiempoDePreparacion > 0){"El tiempo de preparacion debe ser mayor a 0"}
    }


    open fun calcularPrecioFinal(): Double = precio

    override fun toString(): String {
        return "$nombre: \$${"%,.0f".format(calcularPrecioFinal())}"
    }
}

class ProductoComida(
    nombre: String,
    precio: Double,
    tiempoDePreparacion: Int,
    val premium: Boolean //Lo piden en las instrucciones pero no especifican
) : Producto(nombre, precio, "Comida", tiempoDePreparacion) {

    override fun calcularPrecioFinal(): Double {
        return if (premium) precio * 1.15 else precio * 1.00 //Esto tomando en cuenta que si es premium cuesta mas pero no dicen cuanto
    }

    override fun toString(): String {
        if (premium) return "$nombre (premium): \$${"%,.0f".format(calcularPrecioFinal())}"
        else return "$nombre: \$${"%,.0f".format(calcularPrecioFinal())}"
    }

}

class ProductoBebida(
    nombre: String,
    precio: Double,
    tiempoDePreparacion: Int,
    val tamano: String
) : Producto(nombre, precio, "Bebida", tiempoDePreparacion) {

    override fun calcularPrecioFinal(): Double {
        return when(tamano.lowercase()){
            "mediano" -> precio //Precio establecido inicialmente para un tamaño "normal"
            "grande" -> precio * 1.3 //Se le sube un poco al precio por un tamaño mayor
            else -> precio * 0.85 // se le baja un poco al precio por un tamaño mas chico
        }
    }

    override fun toString(): String {
        return "$nombre ($tamano): \$${"%,.0f".format(calcularPrecioFinal())}"
    }
}

abstract class Lugar {}

interface PuntoInteres {
    fun esInteresante(): Boolean
}

open class CiudadComun(var cantidadDeHabitantes: Int = 10000) : PuntoInteres, Lugar() {
    lateinit var plazaHotelera: Number

    override fun esInteresante(): Boolean {
        // la plaza hotelera es el 35% de la cantidad de habitantes
        // actualizamos el valor
        plazaHotelera = cantidadDeHabitantes * 0.35
        return plazaHotelera.toDouble() > 5000
    }
}

// Un parque nacional está cerca de una ciudad
class ParqueNacional(var extension: Int = 10, cantidadDeHabitantes: Int = 10000) : CiudadComun(cantidadDeHabitantes) {
    // un parque nacional es interesante si la ciudad que está cerca es interesante o si tiene una extensión de 50 m2
    override fun esInteresante(): Boolean {
        plazaHotelera = cantidadDeHabitantes * 0.35
        return plazaHotelera.toDouble() > 5000 || extension > 50
    }
}

class Tour {
    var lugares = mutableListOf<PuntoInteres>()
    var estrellas = 3
    var precio = 100.0
    var personasAnotadas = 0
    var comisionACobrar = 0.0
    var maximoPersonas = 5

    // el Tour es interesante si tiene al menos 3 puntos interesantes
    fun esInteresante(): Boolean {
        val puntosInteresantes = mutableListOf<PuntoInteres>()
        lugares.forEach { lugar ->
            if (lugar.esInteresante()) puntosInteresantes.add(lugar)
        }
        return if (puntosInteresantes.size >= 3) true else false
    }

}

object ReporteAgencia {
    fun toursEspeciales() = Agencia.toursEspeciales()
}

object Agencia {
    var tours = mutableListOf<Tour>()

    // Los tours especiales son los que tienen 4 ó 5 estrellas y tienen más de 2 lugares
    fun toursEspeciales(): List<Tour> {
        val toursEspeciales = mutableListOf<Tour>()
        tours.forEach { tour ->
            if (tour.estrellas > 3 && tour.lugares.size > 2) {
                toursEspeciales.add(tour)
            }
        }
        return toursEspeciales
    }

    fun inscribir(tour: Tour, persona: Persona) {
        if (tour.maximoPersonas > tour.personasAnotadas) {
            // Para cobrar la comisión hay que considerar
            // se cobra una comisión de 1 a 5 % en base a las estrellas
            // y hay que sumarle 5% si la persona tiene >= 21 ó 10% en caso contrario
            var comisionACobrar = 0.0
            if (persona.edad >= 21) {
                comisionACobrar = tour.precio * 0.05
            } else {
                comisionACobrar = tour.precio * 0.1
            }
            comisionACobrar = comisionACobrar + (tour.precio * tour.estrellas / 100)
            tour.comisionACobrar = comisionACobrar
            tour.personasAnotadas = tour.personasAnotadas + 1
        }
    }
}

data class Persona(var nombre: String, var edad: Int)
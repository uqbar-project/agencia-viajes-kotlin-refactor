import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class AgenciaViajesSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Tests Ciudad Común") {
        it("es interesante") {
            // Arrange
            val ciudadInteresante = CiudadComun(14286)
            // Assert
            ciudadInteresante.esInteresante() shouldBe true
        }
        it("no es interesante") {
            // Arrange
            val ciudadNoInteresante = CiudadComun(14285)
            // Assert
            ciudadNoInteresante.esInteresante() shouldBe false
        }
    }

    describe("Tests Parque Nacional") {
        it("es interesante por extensión") {
            // Arrange
            val parqueNacionalInteresante = ParqueNacional(51, 100)
            // Assert
            parqueNacionalInteresante.esInteresante() shouldBe true
        }
        it("es interesante porque la ciudad lo es") {
            // Arrange
            val parqueNacionalInteresante = ParqueNacional(51, 50000)
            // Assert
            parqueNacionalInteresante.esInteresante() shouldBe true
        }
        it("no es interesante") {
            // Arrange
            val parqueNacionalInteresante = ParqueNacional(50, 1000)
            // Assert
            parqueNacionalInteresante.esInteresante() shouldBe false
        }
    }

    describe("Tests Tour") {
        it("es interesante") {
            // Arrange
            val tourInteresante = Tour().apply {
                lugares.add(CiudadComun(50000))
                lugares.add(ParqueNacional(60, 5000))
                lugares.add(CiudadComun(2000))
                lugares.add(CiudadComun(70000))
            }
            // Assert
            tourInteresante.esInteresante() shouldBe true
        }

        it("no es interesante") {
            // Arrange
            val tourNoInteresante = Tour().apply {
                // qué pasa si uso un Map
                lugares.add(CiudadComun(50000))
                lugares.add(CiudadComun(2000))
                lugares.add(CiudadComun(70000))
            }

            // Assert
            tourNoInteresante.esInteresante() shouldBe false
        }
    }

    describe("Test de Agencia") {
        describe("tours especiales") {
            it("resuelve los tours especiales") {
                // Arrange
                val tour1 = Tour().apply {
                    estrellas = 2
                    lugares = mutableListOf(CiudadComun(11000))
                }
                val tour2 = Tour().apply {
                    estrellas = 4
                    lugares = mutableListOf(
                        CiudadComun(1000),
                        CiudadComun(1000),
                        CiudadComun(1000),
                    )
                }
                val tour3 = Tour().apply {
                    estrellas = 5
                    lugares = mutableListOf(
                        CiudadComun(1000),
                        CiudadComun(1000),
                    )
                }
                val tour4 = Tour().apply {
                    estrellas = 5
                    lugares = mutableListOf(
                        CiudadComun(1000),
                        CiudadComun(1000),
                        CiudadComun(1000),
                        CiudadComun(1000),
                    )
                }
                Agencia.tours = mutableListOf(tour1, tour2, tour3, tour4)
                Agencia.toursEspeciales() shouldBe listOf(tour2, tour4)
            }
        }

        describe("inscripción de una persona a un tour") {
            it("no inscribe a una persona si no hay cupo") {
                val tour = Tour().apply {
                    maximoPersonas = 1
                    personasAnotadas = 1
                }
                Agencia.inscribir(tour, Persona("Ceci", 20))
                tour.personasAnotadas shouldBe 1
            }

            it("inscribe una persona mayor de edad y calcula las comisiones") {
                val tour = Tour()
                Agencia.inscribir(tour, Persona("Juan", 22))
                // cuántas cosas testeo
                tour.comisionACobrar shouldBe 8.0
                tour.personasAnotadas shouldBe 1
            }

            it("inscribe una persona menor de edad y calcula las comisiones") {
                val tour = Tour()
                Agencia.inscribir(tour, Persona("Fede", 20))
                // cuántas cosas testeo
                tour.comisionACobrar shouldBe 13.0
                tour.personasAnotadas shouldBe 1
            }

        }
    }

})
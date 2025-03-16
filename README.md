
## Ejercicio Refactor Kotlin: Agencia de Viajes

Queremos modelar las vacaciones que ofrece una agencia de viajes, para lo cual sabemos que la agencia ofrece tours que tienen

- un cupo máximo de personas que se pueden anotar
- las calidad, medida en estrellas de 1 a 5
- un precio
- y los lugares a visitar, que pueden ser ciudades comunes o parques nacionales. 

De las ciudades comunes se sabe la cantidad de habitantes que viven en ella, y sabemos que una ciudad común es interesante si su plaza hotelera (el 35% de la cantidad de habitantes) es mayor a 5.000

Los parques nacionales están cerca de una ciudad, y son interesantes si la ciudad de la que están cerca es interesante o bien si la extensión que tiene el parque nacional es mayor a 50 metros cuadrados.

Los requerimientos que se piden son:

- queremos saber si un tour es interesante, esto se da cuando tiene más de 3 lugares interesantes
- queremos que una agencia nos de el reporte de los tours especiales, que son aquellos que tienen más de 3 estrellas y visitan más de 2 lugares
- queremos poder inscribir una persona al tour, para lo cual hay que verificar que no se exceda el máximo de personas del tour (es lo único que hay que validar)
- por último, queremos saber la comisión que cobra la agencia para cada tour, esto se calcula: 
  - de 1 a 5 % en base a las estrellas
  - y hay que sumarle 5% si la persona tiene >= 21 ó 10% en caso contrario

## Qué se pide

Tenemos una solución actual donde tenemos

- [estas definiciones](./src/main/kotlin/ar/edu/algo2/viajes/AgenciaViajes.kt)
- [y estos tests](./src/test/kotlin/ar/edu/algo2/viajes/AgenciaViajesSpec.kt)

Se pide que encuentre code smells y piense refactors para mejorar el código.


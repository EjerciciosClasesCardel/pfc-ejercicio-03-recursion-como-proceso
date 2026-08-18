# Ejercicio 3 — Los procesos que generan las funciones

Fundamentos de Programación Funcional y Concurrente
Escuela de Ingeniería de Sistemas y Computación, Universidad del Valle
Carlos Andrés Delgado Saavedra

La misma función escrita de dos maneras produce dos procesos con costos muy
distintos. Este ejercicio pide escribir las dos y medir la diferencia.

## Las tres formas de proceso

**Lineal recursivo.** Cada llamada deja una operación pendiente que solo se
resuelve al volver. La expresión crece hasta el caso base y después se
contrae. El espacio crece con la entrada.

```scala
def suma(n: Int): Int = if (n <= 0) 0 else n + suma(n - 1)
// suma(4) -> 4 + (3 + (2 + (1 + 0)))
```

**Iterativo.** El resultado parcial viaja en un acumulador y al llegar al caso
base no queda nada pendiente. El espacio es constante.

```scala
@tailrec
final def suma(n: Int, acc: Int = 0): Int = if (n <= 0) acc else suma(n - 1, acc + n)
// suma(4) -> suma(3, 4) -> suma(2, 7) -> suma(1, 9) -> suma(0, 10) -> 10
```

**De árbol.** Cada llamada genera más de una llamada recursiva, así que la
traza se abre en ramas y el número de llamadas crece de forma exponencial.

## Lo que hay que resolver

Todo va en `app/src/main/scala/taller/Ejercicio.scala`.

### Punto 1: la suma de 1 a n, de dos maneras

```scala
def sumaLineal(n: Int): Int
@tailrec final def sumaIterativa(n: Int, acc: Int = 0): Int
```

Las dos devuelven lo mismo: `sumaLineal(5)` y `sumaIterativa(5)` valen 15, y
con 100 las dos dan 5050. La diferencia aparece con una entrada grande: hay
una prueba con un millón, y solo la iterativa la resuelve. La lineal
desbordaría la pila.

`sumaLineal` **no** lleva `@tailrec`, y no debe llevarlo: su gracia es
justamente dejar la operación pendiente.

### Punto 2: Fibonacci, de dos maneras

```scala
def fibArbol(n: Int): Int
@tailrec final def fibIterativo(n: Int, anterior: Int = 0, actual: Int = 1): Int
```

`fibArbol` se escribe con la definición directa: `fib(n) = fib(n-1) +
fib(n-2)`, con `fib(0) = 0` y `fib(1) = 1`. Es un proceso de árbol y
recalcula los mismos valores muchas veces.

`fibIterativo` lleva dos acumuladores con los dos últimos valores. Calcula
cada uno una sola vez, y por eso resuelve `fibIterativo(40)` sin
despeinarse mientras `fibArbol(40)` tardaría un buen rato.

| Llamada | Resultado |
|---|---|
| `fibArbol(0)` | 0 |
| `fibArbol(1)` | 1 |
| `fibArbol(7)` | 13 |
| `fibArbol(15)` | 610 |
| `fibIterativo(40)` | 102334155 |

### Punto 3: contar el trabajo

```scala
def llamadasFibArbol(n: Int): Int
```

Devuelve cuántas llamadas hace `fibArbol` para calcular `fib(n)`, contándose
a sí misma. Es la misma recursión de árbol, pero contando nodos en lugar de
sumando valores: una por la llamada actual más las de sus dos ramas.

| `n` | Llamadas |
|---|---|
| 0 | 1 |
| 1 | 1 |
| 2 | 3 |
| 5 | 15 |
| 10 | 177 |

Ese crecimiento es el punto del ejercicio: para `n = 10` ya son 177 llamadas
para calcular un número que la versión iterativa saca en diez pasos.

## Cómo está organizado el proyecto

```
app/src/main/scala/taller/
    App.scala          programa de arranque
    Ejercicio.scala    aquí van los tres puntos

app/src/test/scala/taller/
    AppSuite.scala        comprueba que el entorno quedó bien
    EjercicioTest.scala   los casos de arriba
```

Su código va en `main`. Las pruebas viven aparte y no se tocan.

## Cómo se ejecuta

```bash
./gradlew test    # corre las pruebas
```

Las pruebas arrancan en rojo y el trabajo es ponerlas en verde. El informe
completo queda en `app/build/reports/tests/test/index.html`.

## Cómo se trabaja

1. Haga fork de este repositorio.
2. En su fork, abra la pestaña **Actions** y habilítelas. GitHub las deja
   desactivadas en las copias hasta que el dueño lo confirme.
3. Clone, resuelva, haga commit y suba a `main`.
4. Verifique en **Actions** que la última ejecución quedó en verde.

## Restricciones

Este curso trabaja sin estado mutable: nada de `var`, `while`, `return` ni
variables que cambien. El resultado correcto por el camino equivocado no
cuenta como resultado correcto.

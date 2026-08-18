package taller

import scala.annotation.tailrec

class Ejercicio {

  // Tal como está, todo devuelve 0 o la lista vacía y las pruebas quedan en
  // rojo. Cada punto pide una forma distinta de proceso.

  /** Proceso recursivo lineal: deja una operación pendiente en cada llamada. */
  def sumaLineal(n: Int): Int = {
    0 // Completar
  }

  /** El mismo resultado como proceso iterativo, con acumulador. */
  @tailrec
  final def sumaIterativa(n: Int, acc: Int = 0): Int = {
    if (n <= 0) acc
    else sumaIterativa(n - 1, acc) // Completar: falta acumular n
  }

  /** Fibonacci con la definición directa: un proceso de árbol. */
  def fibArbol(n: Int): Int = {
    0 // Completar
  }

  /** El mismo Fibonacci como proceso iterativo, con dos acumuladores. */
  @tailrec
  final def fibIterativo(n: Int, anterior: Int = 0, actual: Int = 1): Int = {
    if (n <= 0) anterior
    else fibIterativo(n - 1, anterior, actual) // Completar: falta avanzar
  }

  /** Cuántas llamadas hace fibArbol para calcular fib(n).
    *
    * Es la misma recursión de árbol, contando nodos en lugar de sumando
    * valores: una llamada por el nodo actual más las de sus dos ramas.
    */
  def llamadasFibArbol(n: Int): Int = {
    0 // Completar
  }
}

package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EjercicioTest extends AnyFunSuite {
  val obj = new Ejercicio()

  test("Suma lineal: casos pequeños") {
    assert(obj.sumaLineal(0) == 0)
    assert(obj.sumaLineal(1) == 1)
    assert(obj.sumaLineal(5) == 15)
    assert(obj.sumaLineal(100) == 5050)
  }

  test("Suma iterativa: los mismos resultados") {
    assert(obj.sumaIterativa(0) == 0)
    assert(obj.sumaIterativa(1) == 1)
    assert(obj.sumaIterativa(5) == 15)
    assert(obj.sumaIterativa(100) == 5050)
  }

  test("La versión iterativa aguanta una entrada que desborda a la lineal") {
    assert(obj.sumaIterativa(1000000) == 500000500000L.toInt)
  }

  test("Fibonacci de árbol") {
    assert(obj.fibArbol(0) == 0)
    assert(obj.fibArbol(1) == 1)
    assert(obj.fibArbol(2) == 1)
    assert(obj.fibArbol(7) == 13)
    assert(obj.fibArbol(15) == 610)
  }

  test("Fibonacci iterativo: los mismos resultados") {
    assert(obj.fibIterativo(0) == 0)
    assert(obj.fibIterativo(1) == 1)
    assert(obj.fibIterativo(7) == 13)
    assert(obj.fibIterativo(15) == 610)
  }

  test("El iterativo llega donde el de árbol no") {
    assert(obj.fibIterativo(40) == 102334155)
  }

  test("El número de llamadas del proceso de árbol crece rápido") {
    assert(obj.llamadasFibArbol(0) == 1)
    assert(obj.llamadasFibArbol(1) == 1)
    assert(obj.llamadasFibArbol(2) == 3)
    assert(obj.llamadasFibArbol(5) == 15)
    assert(obj.llamadasFibArbol(10) == 177)
  }
}

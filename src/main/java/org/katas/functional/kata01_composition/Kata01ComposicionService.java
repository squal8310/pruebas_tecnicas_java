package org.katas.functional.kata01_composition;

import org.katas.functional.kata01_composition.model.Producto;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Contrato de la Kata 01: Fundamentos de Programación Funcional y Composición.
 * 
 * Conceptos clave:
 * - Predicate<T> y combinación con and(), or(), negate().
 * - Function<T, R> y composición con andThen(), compose().
 * - High-Order Functions (Funciones que retornan o reciben funciones).
 * - Currying (Evaluación parcial).
 */
public interface Kata01ComposicionService {

    /**
     * Construye un Predicate compuesto para filtrar productos:
     * - Debe pertenecer a la categoría dada (ignorar mayúsculas/minúsculas).
     * - Debe estar activo.
     * - Su precio base debe encontrarse en el rango [precioMin, precioMax] inclusive.
     */
    Predicate<Producto> crearFiltroProducto(String categoria, double precioMin, double precioMax);

    /**
     * Crea una función que aplica una cadena de modificadores de precio usando andThen:
     * 1. Aplicar descuento porcentual (ej. 10% de descuento reduce el precio en 10%).
     * 2. Aplicar un cupón de monto fijo (ej. -50 USD, pero el precio nunca puede ser menor a 0.0).
     * 3. Aplicar impuesto porcentual (ej. +16% de IVA sobre el precio resultante).
     */
    Function<Double, Double> componerPipelinePrecio(double porcentajeDescuento, double cuponFijo, double porcentajeImpuesto);

    /**
     * Currying en Java:
     * Retorna una función de orden superior que recibe una tasa de comisión y retorna
     * OTRA función que recibe el monto y calcula el total con comisión añadida:
     * Formula: monto + (monto * tasaComision)
     * Ejemplo de uso:
     *   Function<Double, Double> conTasa10 = curryingCalculadorComision().apply(0.10);
     *   double total = conTasa10.apply(100.0); // 110.0
     */
    Function<Double, UnaryOperator<Double>> curryingCalculadorComision();

    /**
     * Aplica un predicado y una función de transformación a una lista de productos
     * devolviendo una lista con los nombres de los productos válidos pero convertidos
     * según el transformador de texto.
     */
    List<String> transformarNombres(List<Producto> productos, Predicate<Producto> filtro, Function<String, String> formateador);
}

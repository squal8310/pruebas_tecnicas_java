package org.katas.functional.kata01_composition;

import org.katas.functional.kata01_composition.model.Producto;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * ESPACIO DE TRABAJO DEL ESTUDIANTE:
 * Implementa los métodos usando conceptos de programación funcional pura
 * (lambdas, method references, andThen, compose, etc.).
 */
public class Kata01ComposicionExercise implements Kata01ComposicionService {

    @Override
    public Predicate<Producto> crearFiltroProducto(String categoria, double precioMin, double precioMax) {
        // TODO: Define 3 predicados individuales y combínalos con .and():
        // 1. predicadoCategoria: p.categoria().equalsIgnoreCase(categoria)
        // 2. predicadoActivo: Producto::activo
        // 3. predicadoRangoPrecio: p.precioBase() >= precioMin && p.precioBase() <= precioMax
        throw new UnsupportedOperationException("TODO: Implementa crearFiltroProducto usando composición de Predicate");
    }

    @Override
    public Function<Double, Double> componerPipelinePrecio(double porcentajeDescuento, double cuponFijo, double porcentajeImpuesto) {
        // TODO: Define 3 funciones UnaryOperator<Double> o Function<Double, Double>:
        // 1. aplicarDescuento: precio -> precio * (1.0 - porcentajeDescuento / 100.0)
        // 2. aplicarCupon: precio -> Math.max(0.0, precio - cuponFijo)
        // 3. aplicarImpuesto: precio -> precio * (1.0 + porcentajeImpuesto / 100.0)
        // Concatena las 3 funciones utilizando .andThen(...)
        throw new UnsupportedOperationException("TODO: Implementa componerPipelinePrecio usando andThen");
    }

    @Override
    public Function<Double, UnaryOperator<Double>> curryingCalculadorComision() {
        // TODO: Implementa una función currificada.
        // Recibe 'tasa' y retorna una lambda que recibe 'monto' y devuelve monto * (1.0 + tasa)
        throw new UnsupportedOperationException("TODO: Implementa curryingCalculadorComision");
    }

    @Override
    public List<String> transformarNombres(List<Producto> productos, Predicate<Producto> filtro, Function<String, String> formateador) {
        // TODO: Filtra la lista según 'filtro', extrae el nombre con Producto::nombre,
        // aplica el 'formateador' y retorna una lista inmutable (ej. con .toList()).
        throw new UnsupportedOperationException("TODO: Implementa transformarNombres");
    }
}

package org.katas.functional.kata01_composition.solucion;

import org.katas.functional.kata01_composition.Kata01ComposicionService;
import org.katas.functional.kata01_composition.model.Producto;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Solución de referencia para Kata 01: Composición Funcional.
 */
public class Kata01ComposicionSolution implements Kata01ComposicionService {

    @Override
    public Predicate<Producto> crearFiltroProducto(String categoria, double precioMin, double precioMax) {
        Predicate<Producto> categoriaCoincide = p -> p.categoria().equalsIgnoreCase(categoria);
        Predicate<Producto> estaActivo = Producto::activo;
        Predicate<Producto> rangoPrecio = p -> p.precioBase() >= precioMin && p.precioBase() <= precioMax;

        return categoriaCoincide.and(estaActivo).and(rangoPrecio);
    }

    @Override
    public Function<Double, Double> componerPipelinePrecio(double porcentajeDescuento, double cuponFijo, double porcentajeImpuesto) {
        Function<Double, Double> aplicarDescuento = precio -> precio * (1.0 - (porcentajeDescuento / 100.0));
        Function<Double, Double> aplicarCupon = precio -> Math.max(0.0, precio - cuponFijo);
        Function<Double, Double> aplicarImpuesto = precio -> precio * (1.0 + (porcentajeImpuesto / 100.0));

        return aplicarDescuento
                .andThen(aplicarCupon)
                .andThen(aplicarImpuesto);
    }

    @Override
    public Function<Double, UnaryOperator<Double>> curryingCalculadorComision() {
        return tasa -> monto -> monto * (1.0 + tasa);
    }

    @Override
    public List<String> transformarNombres(List<Producto> productos, Predicate<Producto> filtro, Function<String, String> formateador) {
        return productos.stream()
                .filter(filtro)
                .map(Producto::nombre)
                .map(formateador)
                .toList(); // Java 16+ Stream.toList() produce lista inmutable
    }
}

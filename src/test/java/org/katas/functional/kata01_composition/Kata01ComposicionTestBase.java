package org.katas.functional.kata01_composition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.katas.functional.kata01_composition.model.Producto;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public abstract class Kata01ComposicionTestBase {

    protected abstract Kata01ComposicionService getService();

    @Test
    @DisplayName("Debe filtrar productos combinando predicados con and()")
    void debeFiltrarProductosCombinandoPredicados() {
        Kata01ComposicionService service = getService();
        Predicate<Producto> filtro = service.crearFiltroProducto("Panaderia", 10.0, 50.0);

        Producto prodValido = new Producto("1", "Baguette", 25.0, "panaderia", true);
        Producto prodInactivo = new Producto("2", "Croissant", 30.0, "Panaderia", false);
        Producto prodOtraCat = new Producto("3", "Leche", 25.0, "Lacteos", true);
        Producto prodCaro = new Producto("4", "Pastel Nupcial", 150.0, "PANADERIA", true);
        Producto prodBarato = new Producto("5", "Bolillo", 5.0, "panaderia", true);

        assertThat(filtro.test(prodValido)).isTrue();
        assertThat(filtro.test(prodInactivo)).isFalse();
        assertThat(filtro.test(prodOtraCat)).isFalse();
        assertThat(filtro.test(prodCaro)).isFalse();
        assertThat(filtro.test(prodBarato)).isFalse();
    }

    @Test
    @DisplayName("Debe aplicar pipeline de precio con andThen (descuento -> cupón -> impuesto)")
    void debeComponerPipelinePrecioCorrectamente() {
        Kata01ComposicionService service = getService();

        // 10% descuento, cupón de 10.0, 16% impuesto
        Function<Double, Double> pipeline = service.componerPipelinePrecio(10.0, 10.0, 16.0);

        // Caso 1: precioBase = 100.0
        // Descuento 10% -> 90.0
        // Cupón 10.0    -> 80.0
        // Impuesto 16%  -> 80 * 1.16 = 92.8
        Double resultado = pipeline.apply(100.0);
        assertThat(resultado).isCloseTo(92.8, within(0.001));

        // Caso 2: Cupón excede el precio con descuento -> precio intermedio nunca negativo (0.0)
        // precioBase = 10.0 -> desc 10% = 9.0 -> cupon 10.0 = 0.0 -> impuesto 16% = 0.0
        Double resultadoCero = pipeline.apply(10.0);
        assertThat(resultadoCero).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Debe usar Currying para calcular comisiones con evaluación parcial")
    void debeCalcularComisionConCurrying() {
        Kata01ComposicionService service = getService();

        Function<Double, UnaryOperator<Double>> factoryComision = service.curryingCalculadorComision();

        // Creamos una función especializada fijando la tasa de comisión al 15% (0.15)
        UnaryOperator<Double> comision15 = factoryComision.apply(0.15);

        assertThat(comision15.apply(100.0)).isCloseTo(115.0, within(0.001));
        assertThat(comision15.apply(200.0)).isCloseTo(230.0, within(0.001));

        // Otra función especializada con tasa cero
        UnaryOperator<Double> comisionZero = factoryComision.apply(0.0);
        assertThat(comisionZero.apply(50.0)).isEqualTo(50.0);
    }

    @Test
    @DisplayName("Debe transformar nombres aplicando filtro y función formateadora")
    void debeTransformarNombres() {
        Kata01ComposicionService service = getService();

        List<Producto> productos = List.of(
                new Producto("1", "concha", 15.0, "pan", true),
                new Producto("2", "dona", 12.0, "pan", false),
                new Producto("3", "oreja", 18.0, "pan", true),
                new Producto("4", "jugo", 20.0, "bebida", true)
        );

        Predicate<Producto> soloPanActivo = p -> p.categoria().equals("pan") && p.activo();
        Function<String, String> aMayusculasConPrefijo = nombre -> "[PAN] " + nombre.toUpperCase();

        List<String> resultado = service.transformarNombres(productos, soloPanActivo, aMayusculasConPrefijo);

        assertThat(resultado).containsExactly("[PAN] CONCHA", "[PAN] OREJA");
    }
}

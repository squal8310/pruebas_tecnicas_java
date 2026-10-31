package org.katas.functional.kata02_streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.katas.functional.kata02_streams.model.Categoria;
import org.katas.functional.kata02_streams.model.ItemPedido;
import org.katas.functional.kata02_streams.model.Pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public abstract class Kata02StreamsTestBase {

    protected abstract Kata02StreamsService getService();

    private List<Pedido> muestraPedidos;

    @BeforeEach
    void setUp() {
        ItemPedido laptop = new ItemPedido("i1", "Laptop Pro", 1200.0, 1, Categoria.ELECTRONICA);
        ItemPedido raton = new ItemPedido("i2", "Mouse Inalámbrico", 25.0, 2, Categoria.ELECTRONICA);
        ItemPedido cafe = new ItemPedido("i3", "Café Gourmet", 15.0, 3, Categoria.ALIMENTOS);
        ItemPedido pan = new ItemPedido("i4", "Pan Artesanal", 5.0, 4, Categoria.ALIMENTOS);
        ItemPedido playera = new ItemPedido("i5", "Playera Algodón", 30.0, 2, Categoria.MODA);
        ItemPedido teclado = new ItemPedido("i6", "Teclado Mecánico", 90.0, 1, Categoria.ELECTRONICA);

        Pedido p1 = new Pedido("PED-1", "Carlos", List.of(laptop, raton), LocalDate.now().minusDays(5), true);
        Pedido p2 = new Pedido("PED-2", "Ana", List.of(cafe, pan), LocalDate.now().minusDays(3), true);
        Pedido p3 = new Pedido("PED-3", "Carlos", List.of(playera), LocalDate.now().minusDays(2), false); // No entregado
        Pedido p4 = new Pedido("PED-4", "Beatriz", List.of(teclado, raton), LocalDate.now().minusDays(1), true);
        Pedido p5 = new Pedido("PED-5", "Ana", List.of(cafe), LocalDate.now(), true);

        muestraPedidos = List.of(p1, p2, p3, p4, p5);
    }

    @Test
    @DisplayName("Debe calcular el total de un pedido sumando subtotales")
    void debeCalcularTotalPedido() {
        Kata02StreamsService service = getService();
        Pedido p = muestraPedidos.getFirst(); // Laptop (1200*1) + Mouse (25*2) = 1250.0

        double total = service.calcularTotalPedido(p);
        assertThat(total).isCloseTo(1250.0, within(0.001));
    }

    @Test
    @DisplayName("Debe obtener nombres únicos de productos por categoría ordenados alfabéticamente")
    void debeObtenerNombresProductosUnicosPorCategoria() {
        Kata02StreamsService service = getService();

        List<String> electronica = service.obtenerNombresProductosUnicosPorCategoria(muestraPedidos, Categoria.ELECTRONICA);
        // Laptop Pro, Mouse Inalámbrico (está en PED-1 y PED-4), Teclado Mecánico
        assertThat(electronica).containsExactly("Laptop Pro", "Mouse Inalámbrico", "Teclado Mecánico");

        List<String> alimentos = service.obtenerNombresProductosUnicosPorCategoria(muestraPedidos, Categoria.ALIMENTOS);
        assertThat(alimentos).containsExactly("Café Gourmet", "Pan Artesanal");
    }

    @Test
    @DisplayName("Debe calcular el gasto total de un cliente solo con pedidos entregados")
    void debeCalcularGastoTotalCliente() {
        Kata02StreamsService service = getService();

        // Carlos: PED-1 (1250.0, entregado) + PED-3 (60.0, NO entregado) -> Total = 1250.0
        double gastoCarlos = service.calcularGastoTotalCliente(muestraPedidos, "Carlos");
        assertThat(gastoCarlos).isCloseTo(1250.0, within(0.001));

        // Ana: PED-2 (15*3 + 5*4 = 65.0) + PED-5 (15*1 = 15.0) -> Total = 80.0
        double gastoAna = service.calcularGastoTotalCliente(muestraPedidos, "Ana");
        assertThat(gastoAna).isCloseTo(80.0, within(0.001));
    }

    @Test
    @DisplayName("Debe encontrar el ítem más caro de todos los pedidos")
    void debeEncontrarItemMasCaro() {
        Kata02StreamsService service = getService();

        Optional<ItemPedido> masCaro = service.encontrarItemMasCaro(muestraPedidos);
        assertThat(masCaro).isPresent();
        assertThat(masCaro.get().nombre()).isEqualTo("Laptop Pro");
        assertThat(masCaro.get().precioUnitario()).isEqualTo(1200.0);
    }

    @Test
    @DisplayName("Debe obtener top N clientes ordenados por número de pedidos")
    void debeObtenerTopClientesPorPedidos() {
        Kata02StreamsService service = getService();

        // Carlos: 2, Ana: 2, Beatriz: 1
        List<String> top2 = service.obtenerTopNClientesPorNumeroPedidos(muestraPedidos, 2);
        assertThat(top2).hasSize(2);
        assertThat(top2).contains("Carlos", "Ana");
    }
}

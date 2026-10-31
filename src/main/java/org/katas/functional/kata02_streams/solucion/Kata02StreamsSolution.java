package org.katas.functional.kata02_streams.solucion;

import org.katas.functional.kata02_streams.Kata02StreamsService;
import org.katas.functional.kata02_streams.model.Categoria;
import org.katas.functional.kata02_streams.model.ItemPedido;
import org.katas.functional.kata02_streams.model.Pedido;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Solución de referencia para Kata 02: Inmutabilidad y Stream API.
 */
public class Kata02StreamsSolution implements Kata02StreamsService {

    @Override
    public double calcularTotalPedido(Pedido pedido) {
        return pedido.items().stream()
                .mapToDouble(ItemPedido::subtotal)
                .sum();
    }

    @Override
    public List<String> obtenerNombresProductosUnicosPorCategoria(List<Pedido> pedidos, Categoria categoria) {
        return pedidos.stream()
                .flatMap(p -> p.items().stream())
                .filter(item -> item.categoria() == categoria)
                .map(ItemPedido::nombre)
                .distinct()
                .sorted()
                .toList();
    }

    @Override
    public double calcularGastoTotalCliente(List<Pedido> pedidos, String cliente) {
        return pedidos.stream()
                .filter(Pedido::entregado)
                .filter(p -> p.cliente().equalsIgnoreCase(cliente))
                .mapToDouble(this::calcularTotalPedido)
                .sum();
    }

    @Override
    public Optional<ItemPedido> encontrarItemMasCaro(List<Pedido> pedidos) {
        return pedidos.stream()
                .flatMap(p -> p.items().stream())
                .max(Comparator.comparingDouble(ItemPedido::precioUnitario));
    }

    @Override
    public List<String> obtenerTopNClientesPorNumeroPedidos(List<Pedido> pedidos, int n) {
        return pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::cliente, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }
}

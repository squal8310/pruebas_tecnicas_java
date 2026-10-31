package org.katas.functional.kata02_streams;

import org.katas.functional.kata02_streams.model.Categoria;
import org.katas.functional.kata02_streams.model.ItemPedido;
import org.katas.functional.kata02_streams.model.Pedido;

import java.util.List;
import java.util.Optional;

/**
 * ESPACIO DE TRABAJO DEL ESTUDIANTE:
 * Resuelve las operaciones de Streams de forma puramente declarativa y sin ciclos imperativos (sin for/while).
 */
public class Kata02StreamsExercise implements Kata02StreamsService {

    @Override
    public double calcularTotalPedido(Pedido pedido) {
        // TODO: Usa pedido.items().stream() mapeando al subtotal y sumando con mapToDouble().sum() o .reduce(...)
        throw new UnsupportedOperationException("TODO: Implementa calcularTotalPedido");
    }

    @Override
    public List<String> obtenerNombresProductosUnicosPorCategoria(List<Pedido> pedidos, Categoria categoria) {
        // TODO: pedidos.stream()
        // .flatMap(p -> p.items().stream())
        // .filter(...)
        // .map(...)
        // .distinct()
        // .sorted()
        // .toList()
        throw new UnsupportedOperationException("TODO: Implementa obtenerNombresProductosUnicosPorCategoria");
    }

    @Override
    public double calcularGastoTotalCliente(List<Pedido> pedidos, String cliente) {
        // TODO: Filtra por cliente (equals) y entregado == true,
        // aplanando o mapeando cada pedido a su total y sumando.
        throw new UnsupportedOperationException("TODO: Implementa calcularGastoTotalCliente");
    }

    @Override
    public Optional<ItemPedido> encontrarItemMasCaro(List<Pedido> pedidos) {
        // TODO: Aplanar todos los ítems y usar .max(Comparator.comparingDouble(ItemPedido::precioUnitario))
        throw new UnsupportedOperationException("TODO: Implementa encontrarItemMasCaro");
    }

    @Override
    public List<String> obtenerTopNClientesPorNumeroPedidos(List<Pedido> pedidos, int n) {
        // TODO: Agrupar por Pedido::cliente contando con Collectors.counting(),
        // luego procesar el entrySet ordenando por valor desc, limit(n) y map a Key.
        throw new UnsupportedOperationException("TODO: Implementa obtenerTopNClientesPorNumeroPedidos");
    }
}

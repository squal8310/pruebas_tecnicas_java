package org.katas.functional.kata02_streams;

import org.katas.functional.kata02_streams.model.Categoria;
import org.katas.functional.kata02_streams.model.ItemPedido;
import org.katas.functional.kata02_streams.model.Pedido;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de la Kata 02: Transformaciones con Stream API e Inmutabilidad.
 * 
 * Conceptos clave:
 * - filter, map, flatMap, distinct, sorted, reduce.
 * - Comparator combinator (comparing, naturalOrder, reversed).
 * - Records inmutables y Java 16+ Stream.toList().
 */
public interface Kata02StreamsService {

    /**
     * Calcula la suma de todos los subtotales (precioUnitario * cantidad) de un pedido.
     */
    double calcularTotalPedido(Pedido pedido);

    /**
     * A partir de una lista de pedidos:
     * - Extrae todos los ítems de todos los pedidos (aplanando con flatMap).
     * - Filtra únicamente los ítems que correspondan a la categoría dada.
     * - Obtiene sus nombres.
     * - Elimina duplicados.
     * - Los ordena alfabéticamente en orden ascendente.
     */
    List<String> obtenerNombresProductosUnicosPorCategoria(List<Pedido> pedidos, Categoria categoria);

    /**
     * Calcula el monto total gastado por un cliente específico
     * considerando ÚNICAMENTE los pedidos que ya fueron entregados.
     */
    double calcularGastoTotalCliente(List<Pedido> pedidos, String cliente);

    /**
     * Encuentra el ítem con el precio unitario más alto entre todos los pedidos.
     * Retorna Optional vacío si la lista de pedidos no tiene ítems.
     */
    Optional<ItemPedido> encontrarItemMasCaro(List<Pedido> pedidos);

    /**
     * Obtiene los nombres de los 'n' clientes con mayor cantidad de pedidos registrados,
     * ordenados de mayor a menor cantidad de pedidos.
     */
    List<String> obtenerTopNClientesPorNumeroPedidos(List<Pedido> pedidos, int n);
}

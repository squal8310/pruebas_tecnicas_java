package org.katas.functional.kata02_streams.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Record inmutable que representa un pedido con lista de ítems.
 */
public record Pedido(String idPedido, String cliente, List<ItemPedido> items, LocalDate fecha, boolean entregado) {
    public Pedido {
        items = List.copyOf(items); // Copia defensiva inmutable
    }
}

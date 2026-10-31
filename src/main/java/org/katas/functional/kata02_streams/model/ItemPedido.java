package org.katas.functional.kata02_streams.model;

/**
 * Record inmutable para un ítem dentro de un pedido.
 */
public record ItemPedido(String id, String nombre, double precioUnitario, int cantidad, Categoria categoria) {
    public ItemPedido {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    public double subtotal() {
        return precioUnitario * cantidad;
    }
}

package org.katas.functional.kata01_composition.model;

/**
 * Record que representa un Producto inmutable.
 * Característica de Java 16+.
 */
public record Producto(String id, String nombre, double precioBase, String categoria, boolean activo) {
    public Producto {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo");
        }
    }
}

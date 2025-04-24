package org.global.pan.model;

public class Pan {

    private String nombre;
    private Integer arina;
    private Double huevo;
    private Integer levadura;

    public Integer getArina() {
        return arina;
    }

    public void setArina(Integer arina) {
        this.arina = arina;
    }

    public Double getHuevo() {
        return huevo;
    }

    public void setHuevo(Double huevo) {
        this.huevo = huevo;
    }

    public Integer getLevadura() {
        return levadura;
    }

    public void setLevadura(Integer levadura) {
        this.levadura = levadura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pan{" +
                "nombre='" + nombre + '\'' +
                ", arina=" + arina +
                ", huevo=" + huevo +
                ", levadura=" + levadura +
                '}';
    }
}

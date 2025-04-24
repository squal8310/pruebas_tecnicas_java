package org.global.pan.service;

import org.global.pan.model.Ingrediente;
import org.global.pan.model.Pan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventarioService {

    private Map<String, Object> inventario;

    public InventarioService(){
        this.inventario = new HashMap<>();
    }

    public Map<String, Object> obtenerInventario(){
        return this.inventario;
    }

    public void guardarInventario(String archivoTxt){
        //Obtener el inventario desde archivo TXT y guardar inventario por llave
        //


        Ingrediente harina = new Ingrediente();
        harina.setNombre("Harina");
        harina.setPeso(500);

        Ingrediente huevo = new Ingrediente();
        huevo.setNombre("Huevo");
        huevo.setCantidad(20.0);

        Ingrediente levadura = new Ingrediente();
        levadura.setNombre("Levadura");
        levadura.setPeso(500);

        //Cambiar claves por constantes
        this.inventario.put("HARINA", harina);
        this.inventario.put("HUEVO", huevo);
        this.inventario.put("LEVADURA", levadura);

    }

    public Boolean comprobarInventario(){
        //Al momento de consultar el inventario revisar que ingredientes tenemos disponibles
        //Esto resolvera el f)
        return this.obtenerInventario().isEmpty();
    }
}

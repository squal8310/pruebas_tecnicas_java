package org.global.pan.service;

import org.global.pan.model.Pan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabricaPanService {

    private InventarioService inventario;

    public FabricaPanService(){
        inventario = new InventarioService();
    }

    public Map<String, Object> fabricarPorPiezas(Integer piezas, String tipoPan){
        List<Pan> panLista = new ArrayList<>();
        for(int i=0; i <= piezas; i++){
            Pan pan = new Pan();
            pan.setNombre("Dona");
            pan.setArina(25);
            pan.setHuevo(.25);
            pan.setLevadura(20);
            //Modificar logica para crear el tipo de pan solicitado por parametro
            //Consultar los tipos de panes de un catalogo y al momento de crearlos
            //agregarlos a la lista, por el momento dejaremos el pan por default por
            //cuestiones de pruebas
            panLista.add(pan);
        }
        Map<String, Object> mapaPanCreado = new HashMap<>();
        mapaPanCreado.put(tipoPan, panLista);
        return mapaPanCreado;
    }

    public Boolean validarMateriaPrima(){
        //this.leerInventarios();
        return Boolean.TRUE;
    }

    public void actualizarInventario(String ingrediente){
        //Sustituir logia para hacer match con los requerimientos de los ingredientes
        this.inventario.guardarInventario(ingrediente);
    }

    public void leerInventarios() throws Exception {
        if(this.inventario.comprobarInventario()){
            throw new Exception("No hay suficiente inventario para fabricar su Pancito");
        }
      this.inventario.obtenerInventario();
    }

}

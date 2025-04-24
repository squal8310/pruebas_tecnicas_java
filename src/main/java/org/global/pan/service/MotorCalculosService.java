package org.global.pan.service;

import java.util.*;

public class MotorCalculosService {

    public Double obtenerVarianza(int[] listaNumeros){
        int tamanioLista = listaNumeros.length;
        final Double promedio = obtenerPromedio(listaNumeros, tamanioLista);
        double sumaCuadrados = Arrays.stream(listaNumeros).mapToDouble(elemento -> {
            double diferencia = elemento - promedio;
            return Math.pow(diferencia, 2);
        }).sum();
        return Math.sqrt(sumaCuadrados / tamanioLista);
    }

    public Double obtenerPromedio(int[] listaNumeros, int tamanioLista){
        int sumaLista = Arrays.stream(listaNumeros).sum();
        return (double) sumaLista / (double) tamanioLista;
    }

    public Map<Integer, Integer> calcularFrecuencia(int[] listaNumeros){
        Map<Integer, Integer> numeroSumados = new HashMap<>();
        for(Integer numero: listaNumeros){
                if(numeroSumados.containsKey(numero)){
                    numeroSumados.put(numero, numeroSumados.get(numero)+1);
                }else{
                    numeroSumados.put(numero, 1);
                }
        }
        return numeroSumados;
    }

    public String obtenerModa(Map<Integer, Integer> numeroSumados){
        int moda = 0;
        int mayor = 0;
        for (HashMap.Entry<Integer, Integer> entry : numeroSumados.entrySet()) {
            if (entry.getValue() > mayor) {
                mayor = entry.getValue();
                moda = entry.getKey();
            }
        }
        return new String("La moda es: "+ moda+" porque aparece: "+mayor+" veces.");
    }

    public String obtenerMedia(int[] listaNumeros){
        double media = Arrays.stream(listaNumeros).average().orElse(0.0);
        return "La media del conjunto númerico es: "+Math.ceil(media);
    }

    public String obtenerMediana(int[] listaNumeros){
        int n = listaNumeros.length;
        int mid = n / 2;
        return "La mediana son: "+ Arrays.toString(new int[]{listaNumeros[mid]})+"\n";
    }

}

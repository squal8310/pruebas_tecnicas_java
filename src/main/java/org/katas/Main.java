package org.katas;

import org.global.pan.service.MotorCalculosService;

import javax.swing.*;
import java.util.Arrays;


public class Main {

    private static MotorCalculosService motorCalculosService;

    public Main(){

    }

    public static void main(String[] args) {
        motorCalculosService = new MotorCalculosService();
        String numeros = JOptionPane.showInputDialog("Introduzca el conjunto de números separados por una coma, Ej. 1, 5, 6, 8, 8, 8");
        String[] output = numeros.split("\\s*,\\s*");
        try{
            String conjuntoNuemrico = "Conjunto numerico: " + Arrays.toString(output) + "\n";
            String moda = procesarModa(output, motorCalculosService) + "\n";
            String media = motorCalculosService.obtenerMedia(obtenerNumeros(output)) + "\n";
            String mediana = motorCalculosService.obtenerMediana(obtenerNumeros(output));

            String mensaje = conjuntoNuemrico + moda + media + mediana;
            JOptionPane.showMessageDialog(null, mensaje);
        }catch(RuntimeException e){
            String mensajeError = obtenerMensajeError(e);
            JOptionPane.showMessageDialog(null, mensajeError, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static int[] obtenerNumeros(String[] args){
        return Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
    }

    private static String procesarModa(String[] args, MotorCalculosService motorCalculosService){
        return Main.motorCalculosService.obtenerModa(Main.motorCalculosService.calcularFrecuencia(obtenerNumeros(args)));
    }

    private static String obtenerMensajeError(RuntimeException e){
        if(e instanceof NumberFormatException){
            String formatoException = e.getMessage().split("\\s*:\\s*")[1];
            return "Hay un error por el formato numérico, favor de corregir: "+formatoException;
        }
        return "";
    }
}
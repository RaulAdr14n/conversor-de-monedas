package com.raulhernandez.conversordivisas.calculos;

import java.util.Map;
import java.util.Scanner;

public class Conversion {
    public String monedaBase;
    public String monedaObjetivo;
    private static Map<String, Double> tasasDeConversion;
    Scanner scanner = new Scanner(System.in);

    public Conversion(Map<String, Double> tasasDeConversion) {
        Conversion.tasasDeConversion = tasasDeConversion;
    }

    public String convertirMoneda(String monedaBase, String monedaObjetivo){
        double valorConvertido = 0;

        System.out.println("Ingrese la cantidad de " + monedaBase + " que desea convertir a " + monedaObjetivo);
        
        double valorDeMonedaBase = scanner.nextDouble();
        double valorDeMonedaObjetivo = tasasDeConversion.get(monedaObjetivo);

        valorConvertido = Math.round(valorDeMonedaBase * valorDeMonedaObjetivo * 100.0) / 100.0;

        return valorDeMonedaBase+"("+monedaBase+")"+" ===> "+valorConvertido+"("+monedaObjetivo+")";
    }
    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaObjetivo() {
        return monedaObjetivo;
    }

    public void setMonedaObjetivo(String monedaObjetivo) {
        this.monedaObjetivo = monedaObjetivo;
    }


}

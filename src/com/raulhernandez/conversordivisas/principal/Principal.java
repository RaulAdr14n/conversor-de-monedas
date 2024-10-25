package com.raulhernandez.conversordivisas.principal;
import com.raulhernandez.conversordivisas.calculos.Conversion;
import com.raulhernandez.conversordivisas.modelos.DivisasExchange;
import com.raulhernandez.conversordivisas.modelos.DivisasValidas;
import com.raulhernandez.conversordivisas.services.ApiServicios;
import com.raulhernandez.conversordivisas.services.GsonService;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class Principal {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Gson gson = GsonService.getGson();
            String monedaBase = "";
            String monedaObjetivo = "";
            DivisasExchange divisas = null;
            DivisasValidas divisasValidas = new DivisasValidas();
            int opcion = 0;
        do{
            String json = "";
            System.out.println("*********************************************");
            System.out.println("Elija una opcion:");
            System.out.println("1. ARS => USD");
            System.out.println("2. USD => ARS");
            System.out.println("3. BRL => USD");
            System.out.println("4. USD => BRL");
            System.out.println("5. CLP => USD");
            System.out.println("6. USD => CLP");
            System.out.println("7. Conversion personalizada");
            System.out.println("8. Salir");
            System.out.println("*********************************************");
            while(true){
                try{
                opcion = scanner.nextInt();
                break;
                }catch(InputMismatchException e){
                    System.out.println("Valor no valido, reintentar");
                    continue;
                }
            }
            //Limpiar el buffer
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    monedaBase = "ARS";
                    monedaObjetivo = "USD";
                    break;
                case 2:
                    monedaBase = "USD";
                    monedaObjetivo = "ARS";
                    break;
                case 3:
                    monedaBase = "BRL";
                    monedaObjetivo = "USD";
                    break;
                case 4:
                    monedaBase = "USD";
                    monedaObjetivo = "BRL";
                    break;
                case 5:
                    monedaBase = "CLP";
                    monedaObjetivo = "USD";  
                    break;  
                case 6:
                    monedaBase = "USD";
                    monedaObjetivo = "CLP";  
                    break;    
                case 7:
                    boolean monedasValidas = false;  // Controla el ciclo

                    do {
                        // Pedir moneda base
                        System.out.println("Ingrese la moneda base: ");
                        monedaBase = scanner.nextLine().toUpperCase();

                        // Pedir moneda objetivo
                        System.out.println("Ingrese la moneda a convertir: ");
                        monedaObjetivo = scanner.nextLine().toUpperCase();
                    
                        // Verificar si ambas monedas son válidas
                        if (divisasValidas.esDivisaValida(monedaBase) && divisasValidas.esDivisaValida(monedaObjetivo)) {
                            System.out.println("Monedas válidas");
                            monedasValidas = true;  // Cambia el estado para salir del ciclo
                        } else {
                            System.out.println("Moneda no soportada, intente de nuevo.");
                        }
                    } while (!monedasValidas);  // El ciclo continúa mientras las monedas no sean válidas
                
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    scanner.close();  // Cerrar el scanner antes de salir
                    System.exit(0);   // Detener el programa
                    break;
                default:
                    System.out.println("Opcion fuera de rango");
                    break;
            }
                json = ApiServicios.getApiResponse(monedaBase);
                divisas = gson.fromJson(json, DivisasExchange.class);
                Map<String, Double> tasaDeConversion = divisas.conversionRates();
                Conversion conversionDeDivisa = new Conversion(tasaDeConversion);
                String conversionRealizada = conversionDeDivisa.convertirMoneda(monedaBase, monedaObjetivo);
                System.out.println(conversionRealizada);
            
        }while(true);
        
    }
    

}

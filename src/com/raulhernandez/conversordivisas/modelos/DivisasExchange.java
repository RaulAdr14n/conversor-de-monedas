

package com.raulhernandez.conversordivisas.modelos;
import java.util.Map;


public record DivisasExchange(Map<String , Double> conversionRates ) {

    @Override
    public String toString() {
        return "DivisasExchange []";
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
    

}

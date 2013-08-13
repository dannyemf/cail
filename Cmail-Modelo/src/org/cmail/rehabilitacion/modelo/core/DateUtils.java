/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase de utilidad para fechas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class DateUtils {
    
    /**
     * Obtiene la diferencia en milisegundos entre dos fechas.
     * 
     * @param fechaInicio la fecha inicial
     * @param fechaFinal la fecha final
     * @return los milisegundos
     */
    public static long diferenciaMilisegundos(Date fechaInicio, Date fechaFinal){
        // Crear 2 instancias de Calendar
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Establecer las fechas
        cal1.setTime(fechaInicio);
        cal2.setTime(fechaFinal);

        // conseguir la representacion de la fecha en milisegundos
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();

        // calcular la diferencia en milisengundos
        long diff = milis2 - milis1;
        
        return diff;
    }
    
    /**
     * Obtiene la diferencia en segundos entre dos fechas.
     * 
     * @param fechaInicio la fecha inicial
     * @param fechaFinal la fecha final
     * @return los segundos
     */
    public static long diferenciaSegundos(Date fechaInicio, Date fechaFinal){
        long diff = diferenciaMilisegundos(fechaInicio, fechaFinal);
        
        // calcular la diferencia en segundos
        long diffSeconds = diff / 1000;
        
        return diffSeconds;
        
    }
    
    /**
     * Obtiene la diferencia en minutos entre dos fechas.
     * 
     * @param fechaInicio la fecha inicial
     * @param fechaFinal la fecha final
     * @return los minutos
     */
    public static long diferenciaMinutos(Date fechaInicio, Date fechaFinal){
        long diff = diferenciaMilisegundos(fechaInicio, fechaFinal);                
        // calcular la diferencia en minutos
        long diffMinutes = diff / (60 * 1000);
        
        return diffMinutes;        
    }
    
    /**
     * Obtiene la diferencia en horas entre dos fechas.
     * 
     * @param fechaInicio la fecha inicial
     * @param fechaFinal la fecha final
     * @return las horas
     */
    public static long diferenciaHoras(Date fechaInicio, Date fechaFinal){
        long diff = diferenciaMilisegundos(fechaInicio, fechaFinal);
        
        // calcular la diferencia en horas
        long diffHours = diff / (60 * 60 * 1000);
        
        return diffHours;        
    }
    
    /**
     * Obtiene la diferencia en días entre dos fechas.
     * 
     * @param fechaInicio la fecha inicial
     * @param fechaFinal la fecha final
     * @return los días
     */
    public static long diferenciaDias(Date fechaInicio, Date fechaFinal){
        long diff = diferenciaMilisegundos(fechaInicio, fechaFinal);
        
        // calcular la diferencia en días        
        long diffDays = diff / (24 * 60 * 60 * 1000);
        
        return diffDays;        
    }
    
    /**
     * Pruebas
     * @param args 
     */
    public static void main(String[] args)
    {
        // Crear 2 instancias de Calendar
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Establecer las fechas
        cal1.set(1983, 12, 23);
        cal2.set(2013, 01, 04);

        // conseguir la representacion de la fecha en milisegundos
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();

        // calcular la diferencia en milisengundos
        long diff = milis2 - milis1;

        // calcular la diferencia en segundos
        long diffSeconds = diff / 1000;

        // calcular la diferencia en minutos
        long diffMinutes = diff / (60 * 1000);

        // calcular la diferencia en horas
        long diffHours = diff / (60 * 60 * 1000);

        // calcular la diferencia en dias
        long diffDays = diff / (24 * 60 * 60 * 1000);
        
        long diffMeses = (diff / (24 * 60 * 60 * 1000)) / 30;
        
        long diffAños =  ((diff / (24 * 60 * 60 * 1000)) / 30) / 12;

        System.out.println("En milisegundos: " + diff + " milisegundos.");
        System.out.println("En segundos: " + diffSeconds + " segundos.");
        System.out.println("En minutos: " + diffMinutes + " minutos.");
        System.out.println("En horas: " + diffHours + " horas.");
        System.out.println("En dias: " + diffDays + " dias.");
        System.out.println("En meses: " + diffMeses + " meses.");
        System.out.println("En años: " + diffAños + " años.");
    }
    
}

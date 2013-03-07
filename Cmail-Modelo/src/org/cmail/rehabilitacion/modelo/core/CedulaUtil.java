/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class CedulaUtil {

    GregorianCalendar gc = new GregorianCalendar();

    public static int mt_verificaCedula(String ced) {
        int verificador = 0;
        char num;
        String nu = "";
        int n1 = 0;
        int aux = 0;
        int aux1 = 0;        
        int ver = 0;
        for (int i = 0; i < 10; i = i + 2) {
            num = ced.charAt(i);
            nu = String.valueOf(num);
            n1 = Integer.parseInt(nu);
            n1 = n1 * 2;
            while (n1 > 9) {
                n1 = n1 - 9;
            }
            aux = aux + n1;
        }

        for (int j = 1; j < 9; j = j + 2) {
            num = ced.charAt(j);
            nu = String.valueOf(num);
            n1 = Integer.parseInt(nu);
            aux1 = aux1 + n1;
        }

        verificador = aux + aux1;

        //saca el verificador
        int divid = verificador;
        int divis = divid / 10;
        int res = divid - (10 * divis);
        if (res == 0) {
            ver = res;
        } else {
            ver = 10 - res;
        }
        return ver;
    }

      //para ingresar solo numeros
    /**
     * permite determinar si la cadena ingresada son solo numeros
     * @param cadena - cadena de texto de entrada
     * @return true|false
     */
    public static  boolean mt_soloDigito(String cadena) {        
        boolean b = true;
        for (int i = 0; ((i < cadena.length())&& (b== true)); i++) {
            char cha = cadena.charAt(i);
            b = Character.isDigit(cha);
        }        
        return b;
    }

    public static boolean validar(String cedula) {
        boolean verifica = false;        
        boolean ban = mt_soloDigito(cedula);
        if (ban == false) {
            verifica = false;
        } else {
            if (cedula.length() >= 11) {
                verifica = false;
            } else {
                if (cedula.length() <= 9) {
                    verifica = false;
                } else {
                    int v = mt_verificaCedula(cedula);
                    int dig = Integer.parseInt(String.valueOf(cedula.charAt(9)));
                    if (v == dig) {
                        verifica = true;
                    } else {
                        verifica = false;
                    }
                }
            }
        }
        return verifica;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Clase de utilidad para manejar archivos.
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class FileUtil {

    /**
     * Obtiene los bytes de un archivo
     * 
     * @param file el archivo
     * @return byte[] de contenido del archivo
     * @throws IOException 
     */
    public static byte[] loadData(File file) throws IOException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch;
            while ((ch = in.read()) != -1) {
                buffer.write(ch);
            }
            return buffer.toByteArray();
        } finally {
            try {
                in.close();
            } catch (IOException e2) {
            }
        }
    }

    /**
     * Obtiene el nombre del archivo sin su extensión.
     * 
     * @param file el archivo
     * @return el nombre del archivo
     */
    public static String getSimpleName(File file) {
        String nombre = file.getName();
        int ind = -1;
        int i = -1;
        for (char s : nombre.toCharArray()) {
            i++;
            if (s == '.') {
                ind = i;
            }
        }
        return nombre.substring(0, ind);
    }

    /**
     * Obtiene la extensión de un archivo.
     * 
     * @param file el archivo
     * @return la extensión
     */
    public static String getExtension(File file) {
        String nombre = file.getName();
        int ind = -1;
        int i = -1;
        for (char s : nombre.toCharArray()) {
            i++;
            if (s == '.') {
                ind = i;
            }
        }
        return nombre.substring(ind);
    }
}

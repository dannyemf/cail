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
 *
 * @author Usuario
 */
public class FileUtil {
    
     public static byte[] loadData(File f) throws IOException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(f);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch;
            while ((ch = in.read()) != -1)
                buffer.write(ch);
            return buffer.toByteArray();
        } finally {
            try { in.close(); } catch (IOException e2) {}
        }
    }
     
     public static String getSimpleName(File file){
         String nombre = file.getName();
         int ind = -1;
         int i = -1;
         for (char s : nombre.toCharArray()) {
             i++;
             if(s == '.'){
                 ind = i;
             }
         }         
         return nombre.substring(0, ind);         
     }
     
     public static String getExtension(File file){
         String nombre = file.getName();
         int ind = -1;
         int i = -1;
         for (char s : nombre.toCharArray()) {
             i++;
             if(s == '.'){
                 ind = i;
             }
         }         
         return nombre.substring(ind);         
     }
     
}

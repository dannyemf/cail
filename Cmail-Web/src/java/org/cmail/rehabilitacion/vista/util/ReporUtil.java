/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.util;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.sira.FichaEgreso;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.modelo.sira.VwFichaIngreso;

/**
 * Clase de utilidad para crear los reportes pdf con la librería de JasperReports.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ReporUtil {
    
    public static final Logger log = Logger.getLogger(ReporUtil.class);
    
    public static final String FS = File.separator;
    public  static final String PATH_PLANTILLA = "/org/cmail/rehabilitacion/vista/reporte/";
    public static final String PATH_IMAGEN = "resources"+FS+"icono"+FS+"reporte"+FS;
    public static final String SUFIJO_PLANTILLA = ".jrxml";
    public static final String SUFIJO_PLANTILLA_PRINT = ".jasper";
    
    /**
     * Muestra el reporte
     * @param parametros los parámetros
     * @param listaDatos los datos
     * @param nombrePlantilla la plantilla
     */
    public static void mostrarReporte(Map parametros, List listaDatos, String nombrePlantilla){
        try {
            JasperReport jasperReport = getJasperReport(nombrePlantilla);
            JasperPrint jasperPrint = getJasperPrint(jasperReport, parametros, listaDatos);
            showJasperPrint(jasperPrint);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }                
    }
    
    /**
     * Muestra el reporte
     * @param listaDatos los datos
     * @param nombrePlantilla la plantilla
     */
    public static void mostrarReporte(List listaDatos, String nombrePlantilla){
        //parametros.put("SOLICITANTE", user.getNombres() + " " + user.getApellidos());
        //parametros.put("ESCUDO", ServicioApplication.realPath + PATH_IMAGEN + "logo.jpg");
        //parametros.put("INSTITUCION", pinstitucion.getValor().toUpperCase());
        Map parametros = new HashMap();
        mostrarReporte(parametros, listaDatos, nombrePlantilla);                     
    }
    
    /**
     * Genera el reporte
     * @param parametros
     * @param datos
     * @param nombrePlantilla
     * @return la data
     */
    public static byte[] getReportData(Map parametros,List datos, String nombrePlantilla){
        try {            
            parametros = parametros != null ? parametros : new HashMap();            
            JasperReport jasperReport = getJasperReport(nombrePlantilla);
            JasperPrint jasperPrint = getJasperPrint(jasperReport, parametros, datos);
            
            //JasperPrint jasperPrint = getJasperPrint(nombrePlantilla, parametros, datos);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            
           log.error("Error", e);
            
           ByteArrayOutputStream bo = new ByteArrayOutputStream();
           Document doc = new Document();
            try {
                SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                PdfWriter.getInstance(doc, bo);
                doc.open();
                doc.add(new Paragraph("ERROR AL GENERAR EL REPORTE: " + nombrePlantilla));
                doc.add(new Paragraph());
                doc.add(new Paragraph("-----------------------------------------------------"));
                doc.add(new Paragraph("Fecha: " + sf.format(new Date())));
                doc.add(new Paragraph());
                doc.add(new Paragraph("Estimado usuario.-"));
                doc.add(new Paragraph());
                doc.add(new Paragraph("Le comunicamos que ha ocurrido un error al generar el reporte, por tal motivo le pedimos mil disculpas y le solicitamos comunicarse con el administrador del sistema para informarle del inconveniente."));
                doc.add(new Paragraph());
                doc.add(new Paragraph("Mensaje del sistema: " + e.getMessage()));
                doc.close();
                return bo.toByteArray();
            } catch (Exception ex) {
                log.error("Error", ex);
                return null;
            }                        
        }                
    }
    
    
    /**
     * Obtiene el jasper report compilado
     * @param nombrePlantilla la plantilla
     * @return el reporte
     * @throws Exception 
     */
    private static JasperReport getJasperReport(String nombrePlantilla) throws Exception{        
        InputStream inputStream = null;        
        
        try {            
            inputStream = ReporUtil.class.getResourceAsStream(PATH_PLANTILLA + nombrePlantilla + SUFIJO_PLANTILLA);                                   
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);            
            return jasperReport;
        } catch (Exception e) {
            throw e;
        }finally{
            if(inputStream != null){
                inputStream.close();
            }
        }
    }
    
    /**
     * Precompila un reporte
     * @param nombrePlantilla
     * @throws Exception 
     */
    private static void preCompileTemplate(String nombrePlantilla) throws Exception{        
        try {                        
            String ing = ReporUtil.class.getResource(PATH_PLANTILLA + nombrePlantilla + SUFIJO_PLANTILLA).getPath();
            ing = ing.replace("/build/web/WEB-INF/classes/", "/src/java/");
            String sal = ing.replace(SUFIJO_PLANTILLA, SUFIJO_PLANTILLA_PRINT);
            
            System.out.println("From: " + ing);
            System.out.println("To: " + ing);
            
            JasperCompileManager.compileReportToFile(ing, sal);            
        } catch (Exception e) {
            throw e;
        }finally{            
        }
    }
    
    /**
     * Genera un reporte jasper con su datos llenados
     * @param nombrePlantilla
     * @param parametros
     * @param listaDatos
     * @return
     * @throws Exception 
     */
    private static JasperPrint getJasperPrint(String nombrePlantilla,  Map parametros, List listaDatos) throws Exception{        
        InputStream inputStream = null;
        
        try {      
            inputStream = ReporUtil.class.getResourceAsStream(PATH_PLANTILLA + nombrePlantilla + SUFIJO_PLANTILLA_PRINT);            
            
            JRDataSource dataSource = null;

            if(listaDatos != null){
                 dataSource = new JRBeanCollectionDataSource(listaDatos);
            }else{
                dataSource = new JREmptyDataSource();
                //dataSource = new JRBeanCollectionDataSource(listaDatos);
            }
            
            JasperPrint jasperPrint = JasperFillManager.fillReport( inputStream, parametros, dataSource);            
            return jasperPrint;
        } catch (Exception ex) {            
            throw ex;
        }
    }
    
    /**
     * Genera un reporte jasper con su datos llenados
     * @param jasperReport
     * @param parametros
     * @param listaDatos
     * @return
     * @throws Exception 
     */
    private static JasperPrint getJasperPrint(JasperReport jasperReport, Map parametros, List listaDatos) throws Exception{
        try {      
            JRDataSource dataSource = null;

            if(listaDatos != null){
                 dataSource = new JRBeanCollectionDataSource(listaDatos);
            }else{
                dataSource = new JREmptyDataSource();
                //dataSource = new JRBeanCollectionDataSource(listaDatos);
            }
            
            JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport, parametros, dataSource);            
            return jasperPrint;
        } catch (Exception ex) {            
            throw ex;
        }
    }
    
    /**
     * Muestra el reporte en una ventaja swing
     * @param print 
     */
    private static void showJasperPrint(JasperPrint print){
        try {
            JasperViewer jw = new JasperViewer(print, false);                        
            jw.setVisible(true);                        
        } catch (Exception ex) {            
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }    
    
    /**
     * Agrega parámetros de prueba
     * @param map 
     */
    public static void initMap(Map map){
        map.put("PRM_INSTITUCION", "cmail");        
        map.put("PRM_VISTO", getImage("visto.png"));
        map.put("PRM_LOGO", getImage("logo-reporte.png"));
    }
    
    /**
     * Método de prueba
     */
    public static void reporteFichaIngreso(){
        Map map = new HashMap();
        initMap(map);
        
        FichaIngreso fi = new FichaIngreso();
        fi.setRazInMedidaCautelar(true);
        fi.setCausalVManejarAlcoholizado(true);
        
        Persona p = new Persona();
        p.setSexo("F");
        p.setNombres("xxxx");
        fi.setAdolescente(p);        
        
        map.put("PRM_FICHA", fi);        
        mostrarReporte(map, null, "fichaIngreso");                     
    }

    /**
     * Método de prueba
     */
    public static void reporteFichaEgreso(){
        Map map = new HashMap();   
        initMap(map);
        
        FichaEgreso fi = new FichaEgreso();
        fi.setAceptacionSalida(true);
        
        Persona p = new Persona();
        p.setSexo("F");
        p.setNombres("xxxx");
        fi.setAdolescente(p);        
                
        map.put("PRM_FICHA", fi);
        
        
        mostrarReporte(map, null, "fichaEgreso");                           
    }
    
    /**
     * Método de prueba
     */
    public static void reporteIngresoAdolescentes(){
        Map map = new HashMap();     
        initMap(map);
        
        VwFichaIngreso fi = new VwFichaIngreso();        
        fi.setNombres("Alexandra");
        fi.setApellidos("Maurad");
        fi.setCedula("1104348915");
        fi.setRazonIngreso("medida cualquiera");        
        
        Persona p = new Persona();
        p.setSexo("F");
        p.setNombres("xxxx");
        //fi.setAdolescente(p);        
                
        List n = new ArrayList();
        n.add(fi);
        n.add(fi);
        n.add(fi);
        n.add(fi);
        n.add(fi);
        n.add(fi);
        n.add(fi);
        n.add(fi);
        n.add(fi);               
        
        mostrarReporte(map, n, "ingresoAdolescentes");                           
    }
    
    /**
     * Método de prueba
     */
    public static void reporteInformePsicologico(){
        Map map = new HashMap();   
        initMap(map);                
        
        Persona p = new Persona();
        p.setSexo("F");
        p.setNombres("xxxx");
        
        FichaIngreso fi = new FichaIngreso();        
        fi.setAdolescente(p);
        
        InformePsicologico in = new InformePsicologico();
        in.setAdolescente(p);
        in.setFichaIngreso(fi);
        
                
        map.put("PRM_FICHA", in);
        
        
        mostrarReporte(map, null, "informePsicologico");                           
    }
    
    /**
     * Método de prueba
     */
    public static String getImage(String img){
        return System.getProperty("user.dir") + FS + "web" + FS+"resources"+FS+"icono"+FS+"reporte"+FS+img;
    }
    
    /**
     * Método de prueba
     */
    public static void main(String arg[]){
        //reporteInformePsicologico();
        try {
            //preCompileTemplate("informePsicologico");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(String.format("%s es %s", "x","y"));
    }
    
    /**
     * Método de prueba
     */
    public static BufferedImage getBufferedImage(byte[] data, int w, int h){
        BufferedImage bf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)bf.getGraphics();
        
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, w, h);
        
        if(data != null){
            ImageIcon ic = new ImageIcon(data);            
            g.drawImage(ic.getImage(), 0, 0, w, h, ic.getImageObserver());
        }
        
        g.dispose();
        
        return bf;
    }
    
}

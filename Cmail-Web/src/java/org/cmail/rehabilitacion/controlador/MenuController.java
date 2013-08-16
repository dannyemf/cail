package org.cmail.rehabilitacion.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.modelo.seguridad.VwOpcion;
import org.cmail.rehabilitacion.servicio.OpcionServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.component.submenu.Submenu;
import org.icefaces.ace.model.DefaultMenuModel;


/**
 * Controlador del menú de usuario (barra de menú superior).
 * Se encarga de crear el menú del usuario según sean sus perfiles, así como el control de la navegación entre páginas.
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_MENU)
@SessionScoped
public class MenuController  extends Controller{

    private final String TITULO_HOME = "Cmail - Home";
    private final String TITULO_MAIN = "Cmail - App";
    
    private String pageInclude = "/WEB-INF/include/cmail/content/index.xhtml";
    private String homeInclude = "/WEB-INF/include/home/content/home.xhtml";
    
    private String titulo = TITULO_HOME;    
    private DefaultMenuModel  menuesAplicacion = null;
    
    private String currentPath = "";    
    private List<String> currentActionList = new ArrayList<String>();
    
    /**Constructor por defecto*/
    public MenuController() {
    }            
    
    /**
     * Crea una expresión de método para el oyente de los menús (ActionListener)
     * @param name la expresión. Ejemplo: {bean.nombreListener()}
     * @return el objeto
     */
    public MethodExpressionActionListener createMethodExpressionForMenuActionListener(String name){
        MethodExpression actionListenerExpression = createMethodExpresionForMenuActionListener(name);
        MethodExpressionActionListener meActionListener = new MethodExpressionActionListener(actionListenerExpression);
        return meActionListener;
    }
    
    /**
     * Crea una expresión de método con tres parámetros para el oyente de los menús
     * @param name la expresión. Ejemplo: {bean.nombreListener(evento, url, titulo)}
     * @return el objeto
     */
    public MethodExpression createMethodExpresionForMenuActionListener(String name)
    {
       Class[] argtypes = new Class[3];
       argtypes[0] = ActionEvent.class;
       argtypes[1] = String.class;
       argtypes[2] = String.class;
       
       FacesContext facesCtx = FacesContext.getCurrentInstance();
       ELContext elContext = facesCtx.getELContext();
       return facesCtx.getApplication().getExpressionFactory().createMethodExpression(elContext, name, null, argtypes);
    }
    
    /**
     * Crea el modelo de menú para el usuario logeado considerando los perfiles del mismo.
     * @param usuario el usuario logeado
     */
    public void crearModeloMenu(Usuario usuario) {                     
        
        
        //mia.addActionListener(meActionListener);     
        
        // Crea menu main
        //crearMenuInicio();
        menuesAplicacion = new DefaultMenuModel();
        
        Submenu si = new Submenu();
        si.setId("sumenu-inicio");
        si.setLabel("Inicio");
        menuesAplicacion.addSubmenu(si);
        
        MenuItem mih = new MenuItem();
        mih.setId("menuitem-home");
        mih.setValue("Página Inicio");
        mih.setIcon("ui-icon ui-icon-home");
        si.getChildren().add(mih);
        mih.setActionExpression(createMethodExpressionForAction("#{menuController.actionHomePage}"));
        
        MenuItem mim = new MenuItem();
        mim.setId("menuitem-main");
        mim.setValue("Bienvenida");
        mim.setIcon("ui-icon ui-icon-contact");
        si.getChildren().add(mim);
        mim.setActionExpression(createMethodExpressionForAction("#{menuController.actionCamilPage}"));
        
        MenuItem mis = new MenuItem();
        mis.setId("menuitem-salir");
        mis.setValue("Salir");
        mis.setIcon("ui-icon ui-icon-close");
        si.getChildren().add(mis);
        mis.setActionExpression(createMethodExpressionForAction("#{menuController.actionSalir}"));
        
        List<VwOpcion> opciones = new OpcionServicio().obtenerOpciones(usuario, null);        
        
        for (Iterator<VwOpcion> it = opciones.iterator(); it.hasNext();) {
            VwOpcion opp = it.next();
            log().info("Creando submenu  " + opp.getEtiqueta() + "(" + opp.getId()+")");
            
            Submenu mi = new Submenu();
            mi.setId("submenu-"+opp.getId());
            mi.setLabel(opp.getEtiqueta());
            if(opp.getIcono()!=null && opp.getIcono().trim().length() > 0){
                mi.setIcon(opp.getIcono());
            }
            menuesAplicacion.addSubmenu(mi);
            
            List<VwOpcion> subopciones = new OpcionServicio().obtenerOpciones(usuario, opp);
            crearSubmenu(usuario, opp, mi, subopciones);            
        }
    }
    
    /**
     * Método recursivo que crea los submenús dentro de otro menú, considerando los perfiles del usuario.
     * @param usuario el usuario logeado
     * @param opcion la entidad opción (padre)
     * @param summenu un modelo de vista sobre la que deben agregarse los submenús.
     * @param opciones las subopciones
     */
    public void crearSubmenu(Usuario usuario, VwOpcion opcion, Submenu summenu, List<VwOpcion> opciones) {        
                
        
        for (Iterator<VwOpcion> it = opciones.iterator(); it.hasNext();) {
            VwOpcion op = it.next();
            List<VwOpcion> subopciones = new OpcionServicio().obtenerOpciones(usuario, op);
            
            if(subopciones.isEmpty()){
                
                log().info("Creando menuitem  " + op.getEtiqueta()+"(" + op.getId()+")");
                
                MenuItem mii = new MenuItem();                
                mii.setId("menuitem-"+op.getId());
                mii.setValue(op.getEtiqueta());
                
                MethodExpressionActionListener cme = createMethodExpressionForMenuActionListener("#{menuController.menuActionListener(evt, '"+op.getUrl()+"','"+op.getTitulo()+"')}");
                
                mii.addActionListener(cme);
                if(op.getIcono()!=null && op.getIcono().trim().length() > 0){
                    mii.setIcon(op.getIcono());
                }               
                
                summenu.getChildren().add(mii);                
            }else{
                log().info("Creando submenu  " + op.getEtiqueta()+"(" + op.getId()+")");
                
                Submenu mii = new Submenu();
                mii.setId("submenu-"+op.getId());
                mii.setLabel(op.getEtiqueta());
                if(op.getIcono()!=null && op.getIcono().trim().length() > 0){
                    mii.setIcon(op.getIcono());
                }
                
                summenu.getChildren().add(mii);
                crearSubmenu(usuario, op, mii,subopciones);                            
            }
        }
    }   
    
    /**
     * Evento invocado al hacer click sobre un control de menú.
     * 
     * @param evt el evento lanzado por el control
     * @param url la url que debe presentar
     * @param tit el titulo de la página
     */
    public void menuActionListener(ActionEvent evt, String url, String tit) {        
        log().info("Listener url llamado: " + url + ", titulo: " + tit);

        if (url != null && url.length() > 0) {
            //String tit = FacesUtils.getRequestParameter("titulo");
            currentPath = tit;            
            redirectApp(url);                            
        }
        clearRoute();
    }
    
    /**
     * Obtiene el estilo que debe tener un control de menú de la página principal.
     * @param page la página
     * @return el estilo
     */
    public String estilo(String page){
        if(homeInclude.endsWith(page)){
            return "selected";
        }
        return "";
    }
    
    /**
     * Acción invocada por los menús de la página de inicio (páginas de información al usuario: misión, visión, etc.)
     * @param include la página a cargar
     * @param tit el título de la página
     * @return null, es decir recarga la misma página
     */
    public String homeChangeEvent(String include, String tit) {
        //redirectHome();
        FacesContext context = FacesContext.getCurrentInstance();
        //Map map = context.getExternalContext().getRequestParameterMap();
        //String include = (String) map.get("includePath");
        //String tit = FacesUtils.getRequestParameter("titulo");        
        redirectHome(include);
        setTitulo(tit);
        return null;
    }        

    /**
     * Redirecciona la página cmail.jsf a una nueva url
     * @param ruta la url
     */
    public void redirectApp(String ruta) {
        pageInclude = "/WEB-INF/include/cmail/content/" + ruta; 
        
        try {                    
            FacesContext.getCurrentInstance().getExternalContext().redirect("cmail.jsf");
        } catch (Exception e) {
        }
    }
    
    /**
     * Redirecciona la página home.jsf a una nueva url
     * @param ruta la url
     */
    public void redirectHome(String ruta) {
        homeInclude = "/WEB-INF/include/home/content/" + ruta;
        currentActionList.clear();
    }
    
    /**
     * Redirecciona la página cmail.jsf a la página de bienvenida.
     */
    public void redirectMainApp() {
        pageInclude = "/WEB-INF/include/cmail/content/index.xhtml";
        //template = "/WEB-INF/include/template/cmail.xhtml";
        titulo = TITULO_MAIN;
        currentActionList.clear();
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cmail.jsf");
        } catch (Exception e) {
        }
        
    }
    
    /**
     * Redirecciona la página home.jsf a la página de artículos.
     */
    public void redirectHome() {
        homeInclude = "/WEB-INF/include/home/content/home.xhtml";
        //template = "/WEB-INF/include/template/home.xhtml";
        titulo = TITULO_HOME;
        currentActionList.clear();
    }
    
    /**
     * Redirecciona la página home.jsf a la página de artículos.
     * @return home
     */
    public String actionHomePage() {
        homeInclude = "/WEB-INF/include/home/content/home.xhtml";  
        return "home";
    }
    
    /**
     * Redirecciona la página cmail.jsf a la página de bienvenida.
     * @return cmail
     */
    public String actionCamilPage() {
       pageInclude = "/WEB-INF/include/cmail/content/index.xhtml";  
       currentPath = "";
       currentActionList.clear();
        return "cmail";
    }
    
    /**
     * Cierra la sesión y redirecciona a home
     * @return 
     */
    public String actionSalir() {
       pageInclude = "/WEB-INF/include/cmail/content/index.xhtml";                
       return FacesUtils.getLoginController().logOff();
    }
    
    /**
     * Limpia la última ruta del mapa de navegación (Usted está aquí)
     */
    public void clearLastRoute() {
        if(currentActionList.size() > 0){
            currentActionList.remove(currentActionList.size() - 1);
        }
    }
    
    
    /**
     * Limpia todas las rutas del mapa de navegación (Usted está aquí)
     */
    public void clearRoute() {
        currentActionList.clear();
    }
    
    /**
     * Limpia las n últimas rutas del mapa de navegación (Usted está aquí)
     * @param nLastRutas el número de rutas
     */
    public void clearRoute(int nLastRoutes) {
        while(nLastRoutes > 0){
            try {
                currentActionList.remove(currentActionList.size() - 1);
                nLastRoutes --;
            } catch (Exception e) {
                nLastRoutes = 0;
            }            
        }
    }
    
    /**
     * Agrega la ruta al mapa de navegación (Usted está aquí)
     * @param ruta la ruta
     */
    public void addRoute(String ruta) {
        currentActionList.add(ruta);
    }
    
    /**
     * @return the pageInclude
     */
    public String getPageInclude() {
        return pageInclude;
    }
    
    /**
     * Obtiene la ruta relativa de la página actual
     * @return la ruta
     */
    public String getSimplePageInclude() {
        return pageInclude.replace("/WEB-INF/include/home/content/", "").replace("/WEB-INF/includes/cmail/content/", "");
    }
    
    /**
     * @return the menuesAplicacion
     */
    public DefaultMenuModel getMenuesAplicacion() {
        return menuesAplicacion;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the homeInclude
     */
    public String getHomeInclude() {
        return homeInclude;
    }

    /**
     * @param homeInclude the homeInclude to set
     */
    public void setHomeInclude(String homeInclude) {
        this.homeInclude = homeInclude;
    }

    /**
     * @return the currentPath
     */
    public String getCurrentPath() {
        return currentPath;
    }

    /**
     * @param currentPath the currentPath to set
     */
    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    /**
     * @return the currentAction
     */
    public String getCurrentAction() {
        String currentAction = "";
        
        for (Iterator<String> it = currentActionList.iterator(); it.hasNext();) {
            String s = it.next();
            currentAction += " / " + s;
        }
        
        return currentAction;
    }    
    
}
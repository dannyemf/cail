package org.cmail.rehabilitacion.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
 * <p>The NavigationBean class is responsible for storing the state of the
 * included dynamic content for display.  </p>
 *
 * @since 0.3.0
 */
@ManagedBean(name = Constantes.MB_MENU)
@SessionScoped
public class MenuController  extends Controller{

    private final String TITULO_HOME = "Cmail - Home";
    private final String TITULO_MAIN = "Cmail - App";
    
    private String pageInclude = "/WEB-INF/include/cmail/content/index.xhtml";
    private String homeInclude = "/WEB-INF/include/home/content/home.xhtml";
    
    //private String template = "/WEB-INF/includes/template/home.xhtml";    
    private String titulo = TITULO_HOME;    
    private DefaultMenuModel  menuesAplicacion = null;
    
    private String currentPath = "";    
    private List<String> currentActionList = new ArrayList<String>();
    
    
    public MenuController() {
    }            
    
    public MethodExpressionActionListener createMethodExpressionForMenuActionListener(String name){
        MethodExpression actionListenerExpression = createMethodExpresionForMenuActionListener(name);
        MethodExpressionActionListener meActionListener = new MethodExpressionActionListener(actionListenerExpression);
        return meActionListener;
    }
    
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
    
    public void crearMenu(Usuario usuario) {                     
        
        
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
        mih.setValue("Home");
        mih.setIcon("ui-icon ui-icon-home");
        si.getChildren().add(mih);
        mih.setActionExpression(createMethodExpressionForAction("#{menuController.actionHomePage}"));
        
        MenuItem mim = new MenuItem();
        mim.setId("menuitem-main");
        mim.setValue("Main");
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
    
    
    public void menuActionListener(ActionEvent evt, String url, String tit) {        
        log().info("Listener url llamado: " + url + ", titulo: " + tit);

        if (url != null && url.length() > 0) {
            //String tit = FacesUtils.getRequestParameter("titulo");
            currentPath = tit;            
            redirectApp(url);                            
        }
        clearRoute();
    }    

    public void pageIncludeChange(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String include = (String) map.get("includePath");
        String tit = FacesUtils.getRequestParameter("titulo");
        
        redirectApp(include);
        setTitulo(tit);
    }
    
    public String estilo(String page){
        if(homeInclude.endsWith(page)){
            return "selected";
        }
        return "";
    }
    
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
    
    public String mainAppChangeEvent() {
        redirectMainApp();
        return null;
    }

    public void redirectApp(String ruta) {
        pageInclude = "/WEB-INF/include/cmail/content/" + ruta; 
        
        try {                    
            FacesContext.getCurrentInstance().getExternalContext().redirect("cmail.jsf");
        } catch (Exception e) {
        }
    }        
    
    public void clearLastRoute() {
        if(currentActionList.size() > 0){
            currentActionList.remove(currentActionList.size() - 1);
        }
    }
    
    
    public void clearRoute() {
        currentActionList.clear();
    }
    
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
    
    public void addRoute(String ruta) {
        currentActionList.add(ruta);
    }
    
    public void redirectHome(String ruta) {
        homeInclude = "/WEB-INF/include/home/content/" + ruta;
        currentActionList.clear();
    }
    
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
    
    public void redirectHome() {
        homeInclude = "/WEB-INF/include/home/content/home.xhtml";
        //template = "/WEB-INF/include/template/home.xhtml";
        titulo = TITULO_HOME;
        currentActionList.clear();
    }
    
    public String actionHomePage() {
        homeInclude = "/WEB-INF/include/home/content/home.xhtml";  
        return "home";
    }
    
    public String actionCamilPage() {
       pageInclude = "/WEB-INF/include/cmail/content/index.xhtml";  
       currentPath = "";
       currentActionList.clear();
        return "cmail";
    }
    
    public String actionSalir() {
       pageInclude = "/WEB-INF/include/cmail/content/index.xhtml";                
       return FacesUtils.getLoginController().logOff();
    }
    
    
    
    public String getPageInclude() {
        return pageInclude;
    }
    
    public String getSimplePageInclude() {
        return pageInclude.replace("/WEB-INF/include/home/content/", "").replace("/WEB-INF/includes/cmail/content/", "");
    }
    
    public DefaultMenuModel getMenuesAplicacion() {
        return menuesAplicacion;
    }

    public String getTitulo() {
        return titulo;
    }

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
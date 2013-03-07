
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class NoCachePhaseListener implements PhaseListener {
    public PhaseId getPhaseId() {
 	       return PhaseId.RENDER_RESPONSE;
 	   }
 
 	   public void afterPhase(PhaseEvent phaseEvent) {
 	   }
 
 	   public void beforePhase(PhaseEvent phaseEvent) {
 	       /*FacesContext facesContext = phaseEvent.getFacesContext();
 	       HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
 	       response.addHeader("Pragma", "no-cache");
 	       response.addHeader("Cache-Control", "no-cache");
 	       response.addHeader("Cache-Control", "no-store");
 	       response.addHeader("Cache-Control", "must-revalidate");
 	       response.addHeader("Expires", "Mon, 1 Jan 2006 05:00:00 GMT");//in the past
               */
 	   }
}

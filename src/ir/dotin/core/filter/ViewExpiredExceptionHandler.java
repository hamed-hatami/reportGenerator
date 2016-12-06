package ir.dotin.core.filter;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;

public class ViewExpiredExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public ViewExpiredExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext(); ) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable throwable = context.getException();
            if (throwable instanceof ViewExpiredException) {
                ViewExpiredException vee = (ViewExpiredException) throwable;
                FacesContext facesContext = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
                NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
                try {
                    requestMap.put("currentViewId", vee.getViewId());

                    nav.handleNavigation(facesContext, null, "viewExpired");
                    facesContext.renderResponse();

                } finally {
                    i.remove();
                }
            }
        }
        getWrapped().handle();

    }

}

package ir.dotin.core.converter;

import ir.dotin.core.action.baseInformation.BaseInformationChildAction;
import ir.dotin.core.model.entity.BaseInformationChild;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by h.safyari on 11/14/2016.
 */
@FacesConverter("baseInformationChildConverter")
public class BaseInformationChildConverter implements Converter {

    private BaseInformationChildAction baseInformationChildAction = (BaseInformationChildAction) ManagedBeanManager.lookup(BaseInformationChildAction.class);


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s != null && s.trim().length() > 0) {
            try {
                return baseInformationChildAction.findById(s);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o != null) {
            return String.valueOf(((BaseInformationChild) o).getId());
        }
        else {
            return null;
        }
    }
}

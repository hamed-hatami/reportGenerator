package ir.dotin.core.converter;

import ir.dotin.core.action.baseInformation.BaseInformationParentAction;
import ir.dotin.core.model.entity.BaseInformationParent;
import ir.dotin.core.utils.ManagedBeanManager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by Mr_safiary on 11/6/2016.
 */
@FacesConverter("baseInfoParentConverter")
public class BaseInfoParentConverter implements Converter {

    private BaseInformationParentAction baseInformationParentAction = (BaseInformationParentAction) ManagedBeanManager.lookup(BaseInformationParentAction.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s != null && s.trim().length() > 0) {
            try {
                return baseInformationParentAction.findById(s);
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
            return String.valueOf(((BaseInformationParent) o).getId());
        }
        else {
            return null;
        }
    }
}

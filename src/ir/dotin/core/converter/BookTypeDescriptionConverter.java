package ir.dotin.core.converter;

import org.apache.commons.lang3.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@FacesConverter(value = "bookTypeDescriptionConverter")
public class BookTypeDescriptionConverter implements Converter {


    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return value;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String value = (String) object;
        String description = "";
        try {
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            if (value != null && !StringUtils.isEmpty(value)) {
                //BookAction bookAction = (BookAction) session.getAttribute("bookAction");
                //description = bookAction.findBookTypeDescriptionByBookTypeValue(value);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return description;
    }
}
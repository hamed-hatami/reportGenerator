package ir.dotin.core.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "ibanFormalInformalConverter")
public class IBANFormalInformalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value.replace(" ", "");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return getInformalIBAN(String.valueOf(value));
    }

    private String getInformalIBAN(String rowIban) {
        String informalIBAN = "";
        if (rowIban.length() == 24) {
            informalIBAN = rowIban.substring(0, 2) + " ";
            for (int i = 2; i < 22 && i < rowIban.length(); i += 4) {
                informalIBAN += rowIban.substring(i, i + 4) + " ";
            }
            informalIBAN += rowIban.substring(22, 24);
        } else if (rowIban.length() == 26) {
            for (int i = 0; i < 24; i += 4) {
                informalIBAN += rowIban.substring(i, i + 4) + " ";
            }
            informalIBAN += rowIban.substring(24, 26);
        } else if (rowIban.length() > 1) {
            informalIBAN = rowIban.substring(0, 2) + " ";
            for (int i = 2; i < 24 && i < rowIban.length(); i += 4) {
                if (i + 4 > rowIban.length()) {
                    informalIBAN += rowIban.substring(i, rowIban.length());
                } else {
                    informalIBAN += rowIban.substring(i, i + 4) + " ";
                }
            }
        }


        return informalIBAN.trim();
    }
}

package ir.dotin.core.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.math.BigInteger;
import java.text.DecimalFormat;


@FacesConverter(value = "englishNumberCommaConverter")
public class EnglishNumberCommaConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
            throws ConverterException {
        String number = value.replaceAll(",", "");

        StringBuffer englishNumberString = new StringBuffer();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            switch (c) {
                case '۰':
                    englishNumberString.append('0');
                    break;
                case '۱':
                    englishNumberString.append('1');
                    break;
                case '۲':
                    englishNumberString.append('2');
                    break;
                case '۳':
                    englishNumberString.append('3');
                    break;
                case '۴':
                    englishNumberString.append('4');
                    break;
                case '۵':
                    englishNumberString.append('5');
                    break;
                case '۶':
                    englishNumberString.append('6');
                    break;
                case '۷':
                    englishNumberString.append('7');
                    break;
                case '۸':
                    englishNumberString.append('8');
                    break;
                case '۹':
                    englishNumberString.append('9');
                    break;
                default:
                    englishNumberString.append(c);
            }

        }

        return englishNumberString.toString().trim();
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object)
            throws ConverterException {
        if ((object == null) || (object.equals(null))) {
            return "";
        }

        DecimalFormat df = null;
        String number = "";

        if ((object instanceof BigInteger)) {
            df = new DecimalFormat("###,###,###");
            number = df.format(((BigInteger) object));
        } else if ((object instanceof Long)) {
            df = new DecimalFormat("###,###,###");
            number = df.format(((Long) object));
        } else if ((object instanceof Double)) {
            df = new DecimalFormat("###,###,###");
            number = df.format(((Double) object));
        } else if ((object instanceof Integer)) {
            df = new DecimalFormat("###,###,###");
            number = df.format(((Integer) object));
        } else if ((object instanceof String)) {
            df = new DecimalFormat("###,###,###");
            try {
                number = df.format(Long.valueOf(String.valueOf(object)));
            } catch (Exception e) {
                return String.valueOf(object);
            }
        }

        return number;
    }
}

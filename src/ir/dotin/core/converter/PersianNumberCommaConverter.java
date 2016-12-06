package ir.dotin.core.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.math.BigInteger;
import java.text.DecimalFormat;


@FacesConverter(value = "persianNumberCommaConverter")
public class PersianNumberCommaConverter  implements Converter {

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

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) throws ConverterException {

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
        StringBuffer englishNumberString = new StringBuffer();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            switch (c) {
                case '0':
                    englishNumberString.append('۰');
                    break;
                case '1':
                    englishNumberString.append('۱');
                    break;
                case '2':
                    englishNumberString.append('۲');
                    break;
                case '3':
                    englishNumberString.append('۳');
                    break;
                case '4':
                    englishNumberString.append('۴');
                    break;
                case '5':
                    englishNumberString.append('۵');
                    break;
                case '6':
                    englishNumberString.append('۶');
                    break;
                case '7':
                    englishNumberString.append('۷');
                    break;
                case '8':
                    englishNumberString.append('۸');
                    break;
                case '9':
                    englishNumberString.append('۹');
                    break;
                default:
                    englishNumberString.append(c);
            }

        }

        return englishNumberString.toString();
    }
}
package ir.dotin.core.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "numberLongConverter")
public class NumberLongConverter  implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
            throws ConverterException {
        StringBuffer englishNumberString = new StringBuffer();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
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

        try {
            return Long.valueOf(englishNumberString.toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return englishNumberString.toString();
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object)
            throws ConverterException {
        String number = String.valueOf(object).length() == 1 ? String.valueOf(object).replaceAll("0", "") : String.valueOf(object);

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
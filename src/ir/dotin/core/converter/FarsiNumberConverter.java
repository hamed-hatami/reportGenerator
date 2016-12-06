package ir.dotin.core.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "farsiNumberConverter")
public class FarsiNumberConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        StringBuffer farsiNumberString = new StringBuffer();
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            switch (c) {
                case '۰':
                    farsiNumberString.append('0');
                    break;
                case '۱':
                    farsiNumberString.append('1');
                    break;
                case '۲':
                    farsiNumberString.append('2');
                    break;
                case '۳':
                    farsiNumberString.append('3');
                    break;
                case '۴':
                    farsiNumberString.append('4');
                    break;
                case '۵':
                    farsiNumberString.append('5');
                    break;
                case '۶':
                    farsiNumberString.append('6');
                    break;
                case '۷':
                    farsiNumberString.append('7');
                    break;
                case '۸':
                    farsiNumberString.append('8');
                    break;
                case '۹':
                    farsiNumberString.append('9');
                    break;
                default:
                    farsiNumberString.append(c);
            }
        }
        return farsiNumberString.toString();
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        StringBuffer farsiNumberString = new StringBuffer();
        String number = "";
        if (object instanceof String) {
            number = (String) object;
            for (int i = 0; i < number.length(); ++i) {
                char c = number.charAt(i);
                switch (c) {
                    case '0':
                        farsiNumberString.append('۰');
                        break;
                    case '1':
                        farsiNumberString.append('۱');
                        break;
                    case '2':
                        farsiNumberString.append('۲');
                        break;
                    case '3':
                        farsiNumberString.append('۳');
                        break;
                    case '4':
                        farsiNumberString.append('۴');
                        break;
                    case '5':
                        farsiNumberString.append('۵');
                        break;
                    case '6':
                        farsiNumberString.append('۶');
                        break;
                    case '7':
                        farsiNumberString.append('۷');
                        break;
                    case '8':
                        farsiNumberString.append('۸');
                        break;
                    case '9':
                        farsiNumberString.append('۹');
                        break;
                    default:
                        farsiNumberString.append(c);
                }
            }
        } else {
            return getAsString(facesContext, uiComponent, String.valueOf(object));
        }
        return farsiNumberString.toString();
    }
}
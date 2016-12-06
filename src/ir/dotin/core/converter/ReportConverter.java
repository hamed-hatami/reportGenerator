package ir.dotin.core.converter;

import org.apache.commons.lang3.StringUtils;

public class ReportConverter extends net.sf.jasperreports.engine.JRDefaultScriptlet {

    public String convertDate(String value1) {
        String value = value1;
        if (value != null && !StringUtils.isEmpty(value) && !value.contains("/") && !value.equalsIgnoreCase("null")) {
            if (value.length() == 8)
                value = value.substring(0, 4) + "/" + value.substring(4, 6) + "/" + value.substring(6);
            if (value.length() == 6)
                value = value.substring(0, 4) + "/" + value.substring(4, 6);
            return value;
        } else {
            value = "";
        }
        return value;

    }

    public String convertNumber(String value) {
        StringBuffer farsiNumberString = new StringBuffer();
        String number = "";
        if (value != null) {
            number = value;
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
            farsiNumberString.append("");
        }
        return farsiNumberString.toString();
    }

    public String convertString(String value1) {
        String value = value1;
        if (value != null && !StringUtils.isEmpty(value) && !value.equalsIgnoreCase("null")) {
            return value;
        } else {
            value = "";
        }
        return value;

    }


}
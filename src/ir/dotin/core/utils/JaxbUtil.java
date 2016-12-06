package ir.dotin.core.utils;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;


public class JaxbUtil {

    public synchronized static Object XmlToObject(String xml, String ContextPath) throws JAXBException {
        Object result = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(ContextPath);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            result = (Object) unmarshaller.unmarshal(new StreamSource(new StringReader(xml)));
        } catch (JAXBException e) {
            return null;
        }
        return result;
    }

    public synchronized static String ObjectToXml(Object object, String contextPath) {

        String result = "";
        StringWriter sw = new StringWriter();

        try {
            JAXBContext jc = JAXBContext.newInstance(contextPath);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.marshal(object, sw);
            result = sw.toString();

        } catch (JAXBException e) {
            return null;
        }
        return result;
    }

}

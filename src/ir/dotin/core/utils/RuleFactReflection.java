package ir.dotin.core.utils;


import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RuleFactReflection {

    public static List<Class<?>> getRuleFacts() {

        List<Class<?>> clazz = new ArrayList<Class<?>>();
        try {
            FileInputStream fileInputStream = new FileInputStream(Configuration.getProperty("jboss.name") + File.separatorChar + Configuration.getProperty("jboss.server") + File.separatorChar + "deployments" + File.separatorChar + Configuration.getProperty("app.name") + ".war");
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            ZipEntry zipEntry = null;
            while ((zipEntry = zip.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                if (entryName.contains(Configuration.getProperty("rule.fact.package").replace('.', '/')) && entryName.endsWith(".class")) {
                    String className = Configuration.getProperty("rule.fact.package") + '.' + entryName.substring(entryName.indexOf("fact") + 5, entryName.length() - 6);
                    clazz.add(Class.forName(className));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazz;
    }

    public synchronized static List<String> getRuleFactMethods(Class<?> clazz) {

        List<String> fields = new ArrayList<String>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field.getName());
        }
        return fields;
    }

}

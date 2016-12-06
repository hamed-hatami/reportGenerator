package ir.dotin.core.utils;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {

    public synchronized static List<String> unPack(String parameters) {
        String dataPart = parameters.substring(parameters.indexOf("#") + 1, parameters.length());

        List<String> data = new ArrayList<String>();

        if (dataPart.indexOf("#") != -1) {
            String[] objects = dataPart.split("#");
            for (int counter = 0; counter < objects.length; counter++) {
                String[] fields = objects[counter].split(",");
                for (int innerCounter = 0; innerCounter < fields.length; innerCounter++) {
                    data.add(fields[innerCounter]);
                }
            }
        } else {
            String[] fields = dataPart.split(",");
            for (int counter = 0; counter < fields.length; counter++) {
                data.add(fields[counter]);
            }
        }

        return data;
    }
}

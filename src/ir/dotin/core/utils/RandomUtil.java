package ir.dotin.core.utils;


import java.util.Random;


public class RandomUtil {

    public String randomString(final int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char c = (char)(r.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    public byte[] randomByteString(final int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString().getBytes();
    }

    public String getRandomColor() {
        String[] letters = new String[15];
        letters = "0123456789ABCDEF".split("");
        String code = "#";
        for (int i = 0; i < 6; i++) {
            double ind = Math.random() * 15;
            int index = (int) Math.round(ind);
            code += letters[index];
        }
        return code;
    }
}
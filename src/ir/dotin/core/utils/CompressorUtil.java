package ir.dotin.core.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class CompressorUtil {

    public String compress(byte[] content) {
        try {
            byte[] compressed = new byte[0];
            byte[] blockcopy = ByteBuffer
                    .allocate(4)
                    .order(java.nio.ByteOrder.LITTLE_ENDIAN)
                    .putInt(content.length)
                    .array();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(content.length);
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
            gzipOutputStream.close();
            compressed = new byte[4 + byteArrayOutputStream.toByteArray().length];
            System.arraycopy(blockcopy, 0, compressed, 0, 4);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, compressed, 4,
                    byteArrayOutputStream.toByteArray().length);
            return org.apache.commons.codec.binary.Base64.encodeBase64String(compressed);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public byte[] compressByte(byte[] content) {
        try {
            byte[] compressed = new byte[0];
            byte[] blockcopy = ByteBuffer
                    .allocate(4)
                    .order(java.nio.ByteOrder.LITTLE_ENDIAN)
                    .putInt(content.length)
                    .array();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(content.length);
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
            gzipOutputStream.close();
            compressed = new byte[4 + byteArrayOutputStream.toByteArray().length];
            System.arraycopy(blockcopy, 0, compressed, 0, 4);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, compressed, 4,
                    byteArrayOutputStream.toByteArray().length);
            return compressed;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public byte[] decompress(String content) {
        try {
            byte[] compressed = org.apache.commons.codec.binary.Base64.decodeBase64(content);
            GZIPInputStream gzipInputStream = new GZIPInputStream(
                    new ByteArrayInputStream(compressed, 4,
                            compressed.length - 4));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int value = 0; value != -1; ) {
                value = gzipInputStream.read();
                if (value != -1) {
                    byteArrayOutputStream.write(value);
                }
            }
            gzipInputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] decompress(byte[] content) {
        try {
//            byte[] compressed = org.apache.commons.codec.binary.Base64.decodeBase64(content);
            GZIPInputStream gzipInputStream = new GZIPInputStream(
                    new ByteArrayInputStream(content, 4,
                            content.length - 4));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int value = 0; value != -1; ) {
                value = gzipInputStream.read();
                if (value != -1) {
                    byteArrayOutputStream.write(value);
                }
            }
            gzipInputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

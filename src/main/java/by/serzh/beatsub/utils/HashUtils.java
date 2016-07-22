package by.serzh.beatsub.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    private HashUtils() { }

    public static String md5(String text) {
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] bytesOfMessage = text.getBytes("UTF-8");
            byte[] digest = md5Digest.digest(bytesOfMessage);
            String result = new String(Hex.encodeHex(digest));
            return result;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}

package server.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    MessageDigest md;

    public HashPassword(String nameOfHash) throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance(nameOfHash);
    }

    public byte[] hashPassword(String password) {
        byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
        return md.digest(bytesOfPassword);
    }

}

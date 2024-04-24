package com.cedricverlinden.banking.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hasher {

    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;
    private static final int ITERATIONS = 100_000;
    private static final byte[] SALT;

    static {
        SecureRandom random = new SecureRandom();
        SALT = new byte[SALT_LENGTH];
        random.nextBytes(SALT);
    }

    // public static byte[] generateSalt() throws NoSuchAlgorithmException {
    //     SecureRandom random = new SecureRandom();
    //     byte[] salt = new byte[SALT_LENGTH];
    //     random.nextBytes(salt);
    //     return salt;
    // }

    public static byte[] hashPassword(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT, ITERATIONS, HASH_LENGTH * 8);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return factory.generateSecret(spec).getEncoded();
    }

    public static String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static boolean verifyPassword(String inputPassword, byte[] storedHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] inputHash = hashPassword(inputPassword);

        if (inputHash.length != storedHash.length) {
            return false;
        }

        for (int i = 0; i < inputHash.length; i++) {
            if (inputHash[i] != storedHash[i]) {
                return false;
            }
        }

        return true;
    }
}

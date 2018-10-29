package org.xiyoulinux.recruitment.untils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RsaUntil {
    KeyPairGenerator keyPairGenerator;
    public RsaUntil() throws NoSuchAlgorithmException {
        keyPairGenerator =  KeyPairGenerator.getInstance("rsa");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

    }
}

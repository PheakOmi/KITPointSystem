package com.EncryptionDecryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import sun.misc.BASE64Decoder;
public class Decryption{

	 /**
     * Decrypts encrypted byte array using the key used for encryption.
     * @param cipherText
     * @param secKey
     * @return
     * @throws Exception 
     */
    public String decryptText(String cipherText, SecretKey secKey) throws Exception {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
    	byte[] bytesText = new BASE64Decoder().decodeBuffer(cipherText);
        Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(bytesText);
        return new String(bytePlainText);
    }

}

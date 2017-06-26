package com.odolgikh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import okio.ByteString;

/**
 * Created by olegdolgikh on 4/13/17.
 */
public class Main {

    public static void main(String[] args) {
        try {
            if (args == null || args.length == 0) {
                System.out.println("you must specify path to ssl certificate file");
                return;
            }
            CertificateFactory certFactory;
            certFactory = CertificateFactory.getInstance("X.509");
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(args[0]);
                X509Certificate cert = (X509Certificate) certFactory.generateCertificate(inputStream);
                System.out.println(("[CERT] = " + ByteString.of(cert.getPublicKey().getEncoded()).sha1().base64()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }
}

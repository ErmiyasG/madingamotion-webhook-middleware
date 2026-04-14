package org.postwork.er.madingamotion.security;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class SignatureValidator {

    private static final String SECRET = "SDF23423sdfudsdf";

    public boolean isValid(String payload, String signature) {
        try {
            String computed = hmacSha256(payload);
            return computed.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }

    private String hmacSha256(String data) throws Exception {

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(SECRET.getBytes(), "HmacSHA256"));
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    }

}

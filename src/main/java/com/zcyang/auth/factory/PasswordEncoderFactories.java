package com.zcyang.auth.factory;

import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class PasswordEncoderFactories {

    public static PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "md5_cts";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new MD5PasswordEncoder());

        return new DelegatingPasswordEncoder(encodingId, encoders);
    }
}

package com.example.assignment03.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Service
public class FormatUtils {

    @Autowired
    private JsonMapper jsonMapper;
    private static final String AUTHORIZATION = "Authorization";

    public static String parseJWT(String jwtToken){
        String[] arr = jwtToken.split("\\.");
        String base64EncodedBody = arr[1];
        Base64 base64Url = new Base64(true);
        return new String(base64Url.decode(base64EncodedBody));
    }

    public Map<String, Object> getIdEntity(String jwt, HttpServletRequest request) throws JsonProcessingException {
        if (jwt == null) {
            jwt = request.getHeader(AUTHORIZATION);
        }
        String tokenValue = jwt.replace("Bearer", "").trim();
        Map<String, Object> jwtBody = jsonMapper
                .readValue(FormatUtils.parseJWT(tokenValue), new TypeReference<Map<String, Object>>() {
                });
        Map<String, Object> idEntity = (Map<String, Object>) jwtBody.get("email");
        return idEntity;

    }

}

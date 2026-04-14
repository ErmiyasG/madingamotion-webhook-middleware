package org.postwork.er.madingamotion.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;

@Component
public class WebhoookInterceptor implements HandlerInterceptor {

    @Autowired
    private SignatureValidator validator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String signature = request.getHeader("X-signature");

        StringBuilder body = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        if (signature == null || validator.isValid(body.toString(), signature)) {
            response.setStatus(401);
            response.getWriter().write("Invalid signature");
            return false;
        }
        return true;
    }


}

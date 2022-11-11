package br.com.jwt.jwt.config;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.jwt.jwt.constants.SecurityConstantes;
import br.com.jwt.jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    @Autowired
    UserService userService;

    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		UserDetails user = (UserDetails) authentication.getPrincipal();

        String token = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(Instant.ofEpochMilli(
                ZonedDateTime.now(ZoneId.systemDefault()).toInstant()
            .toEpochMilli() + SecurityConstantes.EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SecurityConstantes.TOKEN_PASSWORD));
            
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-Type","application/json");
    	response.getWriter().write("{\"token\": \"" + token + "\"}");
    }

}

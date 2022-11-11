package br.com.jwt.jwt.config;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.jwt.jwt.constants.SecurityConstantes;
import br.com.jwt.jwt.service.UserServiceImpl;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    UserServiceImpl userServiceImpl;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserServiceImpl userServiceImpl) {
        super(authenticationManager);
        this.userServiceImpl = userServiceImpl;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(SecurityConstantes.HEADER_AUTH);
        if (header == null || !header.startsWith(SecurityConstantes.PARAM_BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken auth = getAuthentication(request);

        if (auth == null) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstantes.HEADER_AUTH);

        try {

            if (token != null) {

                String userMail = JWT.require(Algorithm.HMAC512(SecurityConstantes.TOKEN_PASSWORD))
                        .build()
                        .verify(token.replace(SecurityConstantes.PARAM_BEARER, ""))
                        .getSubject();
                if (userMail != null) {
                    UserDetails userDetails = userServiceImpl.loadUserByUsername(userMail);
                    return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
                            userDetails.getAuthorities());

                }
                return null;
            }
        } catch (com.auth0.jwt.exceptions.TokenExpiredException e) {
            System.out.println("Token Expirado");
        }

        return null;
    }

}

package br.com.jwt.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.jwt.jwt.service.UserService;
import br.com.jwt.jwt.service.UserServiceImpl;


@Configuration
public class SecurityConfiguration {
	
	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AuthenSuccessHandler authenSuccessHandler;
		
	
	@Autowired
	UserService service;
	
	private static final String[] AUTH_WHITELIST = {
		// -- swagger ui
		"/v2/api-docs",
		"/v3/api-docs",  
		"/swagger-resources/**", 
		"/swagger-ui/**",
		"/swagger-ui.html",
		"/webjars/**"
	};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		    	
        http
        	.cors()
        	.and() 
			.csrf().disable()  	
            .authorizeHttpRequests((auth) -> {
			    try {
					auth
						.antMatchers(HttpMethod.POST, "/login").permitAll()
						.antMatchers(AUTH_WHITELIST).permitAll()
						.antMatchers(HttpMethod.GET, "/auth/findall").hasRole("ADMIN")
						.antMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")
						.antMatchers(HttpMethod.PUT, "/auth/update").hasRole("USER")
						.antMatchers(HttpMethod.GET, "/produto/todos").hasRole("USER")
						.antMatchers(HttpMethod.GET, "/produto/todos").hasRole("USER")
						.antMatchers(HttpMethod.GET, "/produto/um/**").hasRole("USER")
						.antMatchers(HttpMethod.POST, "/produto/salva").hasRole("ADMIN")
						.antMatchers(HttpMethod.PUT, "/produto/atualiza").hasRole("ADMIN")
						.antMatchers(HttpMethod.DELETE, "/produto/delete/**").hasRole("ADMIN")
						.anyRequest().authenticated()
						.and()
						.addFilter(authenticationFilter())
                        .addFilter(new AuthorizationFilter(authenticationManager, userServiceImpl))
                        .authenticationManager(authenticationManager)
						.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.exceptionHandling()
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
						.and()
						.logout()
						.logoutSuccessUrl("/index")
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
						
					
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
				})
				
			.httpBasic(Customizer.withDefaults());         
        return http.build();
    }
    
    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
    	JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
    	filter.setAuthenticationSuccessHandler(authenSuccessHandler);
    	filter.setAuthenticationManager(authenticationManager);
    	return filter;
    }
    
    
   
    
    
   
}


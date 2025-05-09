package com.uacm.aktivai.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ManejadorDeAutenticacionFallida implements AuthenticationFailureHandler{
	
	private static final String BAD_CREDENTIALS = "Bad credentials";
	private static final String USER_IS_DISABLED = "User is disabled";
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//if(log.isDebugEnabled())
			//log.debug("Excepcion: " + exception.getMessage());

		if (exception != null) {
			
			if(exception.getMessage().equals(USER_IS_DISABLED)) {
				//Usuario deshabilitado
				redirectStrategy.sendRedirect(request, response, "/index.html?userDisabled=true");
			} else if(exception.getMessage().equals(BAD_CREDENTIALS)) {
				//Bad credentials
				redirectStrategy.sendRedirect(request, response, "/index.html?badCredentials=true");
			} 
		}
	}
		
}



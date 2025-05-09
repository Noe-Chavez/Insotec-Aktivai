package com.uacm.aktivai.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uacm.aktivai.model.Rol;
import com.uacm.aktivai.model.Usuario;
import com.uacm.aktivai.model.repository.RolRepository;
import com.uacm.aktivai.model.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DetalleUsuarioServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	
	@Autowired
	private HttpServletRequest request;
	

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		if(log.isDebugEnabled())
			log.debug("Entrando a loadUserByUsername");
		String ip = request.getRemoteAddr();
		
		try {
            final Usuario user = usuarioRepository.findByMail(email);
           
            if (user == null) {
            	 
                return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true, getAuthorities(Arrays.asList(rolRepository.findByNombre("ROLE_USUARIO"))));
            }
            
            if (log.isDebugEnabled())
                log.debug("******** usuario*******: " + user);

            return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), user.isHabilitado(), true, true, true, getAuthorities(user.getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}
	
	public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Rol> roles) {
        return getGrantedAuthorities(getRoles(roles));
    }
	
    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    
    private List<String> getRoles(final Collection<Rol> roles) {
    	final List<String> finalRoles = new ArrayList<String>();
    	
    	for (Rol role : roles) {
            finalRoles.add(role.getNombre());
        }
    	
    	return finalRoles;
    }
	
	

}

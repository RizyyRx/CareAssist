package com.hexaware.project.CareAssist.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hexaware.project.CareAssist.dto.LoginDTO;
import com.hexaware.project.CareAssist.jwt.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService{

	private final AuthenticationManager authenticationManager ;
    private final JwtTokenProvider jwtTokenProvider; // inject it
    
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
    
    @Override
    public String login(LoginDTO loginDTO) {

    	// calls CustomUserDetailsService for authentication
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        		loginDTO.getUsernameOrEmail(),
        		loginDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}

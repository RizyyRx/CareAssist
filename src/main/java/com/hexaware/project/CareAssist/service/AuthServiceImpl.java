package com.hexaware.project.CareAssist.service;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.project.CareAssist.dto.LoginDTO;
import com.hexaware.project.CareAssist.dto.RegisterDTO;
import com.hexaware.project.CareAssist.entity.Role;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.jwt.JwtTokenProvider;
import com.hexaware.project.CareAssist.repository.RoleRepository;
import com.hexaware.project.CareAssist.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	private final AuthenticationManager authenticationManager ;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    
	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
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

	@Override
	public String register(RegisterDTO registerDTO) {

		User user = new User();
	    user.setUsername(registerDTO.getUsername());
	    user.setEmail(registerDTO.getEmail());
	    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

	    String requestedRole = registerDTO.getRole().toUpperCase();

	    // Only allow specific roles from client
	    if (!requestedRole.equals("PATIENT") &&
	        !requestedRole.equals("HEALTHCARE_PROVIDER") &&
	        !requestedRole.equals("INSURANCE_COMPANY")) {
	        throw new RuntimeException("Invalid role");
	    }
	    
	    Role role = roleRepository.findByName("ROLE_" + requestedRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));

	    // Put role obj into a Set and set it to user obj
	    user.setRoles(Set.of(role));
	
	    userRepository.save(user);
	    
	    return "User registered successfully with role: " + registerDTO.getRole();
		
	}
}

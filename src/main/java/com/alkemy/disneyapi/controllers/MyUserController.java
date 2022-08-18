package com.alkemy.disneyapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disneyapi.dto.AuthenticationRequest;
import com.alkemy.disneyapi.dto.AuthenticationResponse;
import com.alkemy.disneyapi.dto.UserDTO;
import com.alkemy.disneyapi.security.UserDetailsCustomService;
import com.alkemy.disneyapi.services.JwtUtils;

@RestController
@RequestMapping("/auth")
public class MyUserController {
	

	private UserDetailsCustomService userDetailsCustomService;
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;
	
	
	@Autowired
	public MyUserController(
			UserDetailsCustomService userDetailsCustomService, 
			AuthenticationManager authenticationManager,
			JwtUtils jwtUtils) {
		this.userDetailsCustomService=userDetailsCustomService;
		this.authenticationManager=authenticationManager;
		this.jwtUtils = jwtUtils;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthenticationResponse> signUp(@RequestBody UserDTO user) throws Exception{
		this.userDetailsCustomService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> signUp(@RequestBody AuthenticationRequest request) throws Exception{
		UserDetails userDetails;
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			userDetails = (UserDetails) auth.getPrincipal();
			 
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}	
		final String jwt = jwtUtils.generateToken(userDetails);		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	
	
	

}
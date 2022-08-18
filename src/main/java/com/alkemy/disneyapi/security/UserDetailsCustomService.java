package com.alkemy.disneyapi.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkemy.disneyapi.dto.UserDTO;
import com.alkemy.disneyapi.entities.MyUser;
import com.alkemy.disneyapi.repositories.MyUserRepository;
import com.alkemy.disneyapi.services.EmailService;

@Service
public class UserDetailsCustomService implements UserDetailsService {
	
	@Autowired
	MyUserRepository userRepo;
	
//	@Autowired
//	private EmailService emailService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found.");
		}
		
		return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
	}

	public boolean save(UserDTO userDTO) {
		MyUser user = new MyUser();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user = userRepo.save(user);
		
//		if(user!=null) {
//			emailService.sendWelcomeEmailTo(user.getUsername());
//		}
		
		return user!=null;
	}
	
}

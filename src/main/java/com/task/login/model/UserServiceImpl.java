package com.task.login.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.login.repository.UserRepository;

import java.util.*;
//import java.util.Base64.Encoder;



@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	
	@Autowired
	private UserRepository userRepo;
		
	List<String> jwtPermissions = new ArrayList<>();
	
/*	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);		
		mailSender.setUsername("sodan.nagar59@gmail.com");
		mailSender.setPassword("7737274168");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}*/
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}

	// get authorities for user based on role....
	private List<SimpleGrantedAuthority> getAuthority(User user) {
		
		jwtPermissions.clear();
		
		return Arrays.asList(new SimpleGrantedAuthority(jwtPermissions.toString()));
	}
	
	
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	/*@Override
	public User findOne(String username) {
		return userRepo.findByEmail(username);
	}*/
	
/*	@Override
	public User findById(int userId) {
		return userRepo.findUserById(userId);
	}
*/
	//saving user information
	/*@Override
    public User save(User user) {
	    User newUser = new User();
	    
	    newUser.setEmail(user.getEmail());
	    
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	    
		
        return userRepo.save(newUser);
    }*/
	
}

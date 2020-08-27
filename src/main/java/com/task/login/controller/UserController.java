package com.task.login.controller;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.login.exceptionhandler.BadRequest400;
import com.task.login.model.APIConstant;
import com.task.login.model.APIResponse;
import com.task.login.model.OtpDto;
import com.task.login.model.User;
import com.task.login.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	private final UserRepository userRepo;
	
	// injecting repositories using constructor
	@Autowired
	public UserController(UserRepository userRepo) {
		this.userRepo=userRepo;		
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("ram.nagarr9950@gmail.com");
		mailSender.setPassword("test@12345");
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}

	
	@PostMapping("/signup")
	public APIResponse<Object> addUser(@RequestBody User user) {

		System.out.println("user====="+user);
		Map<String, String> map = new LinkedHashMap<>();

		APIResponse<Object> resp = new APIResponse<Object>();

		User findByEmail = userRepo.findByEmail(user.getEmail());
		System.out.println("findByEmail====="+findByEmail);
		if (findByEmail == null) {

			System.out.println("1=====");
			user.setPassword(encoder().encode(user.getPassword()));

			String numbers = "1234567890";
			Random random = new Random();
			char[] otp = new char[4];

			System.out.println("2=====");
			for (int i = 0; i < 4; i++) {
				otp[i] = numbers.charAt(random.nextInt(numbers.length()));
			}
			user.setOtp(Integer.parseInt(String.valueOf(otp)));

			User userObj = userRepo.save(user);

			System.out.println("user==="+user);
			
			if (userObj != null) {

				System.out.println("inside if ===");
				try{
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(user.getEmail());
				message.setSubject("Welcome Mail");
				message.setText(
						"Thank you for registration. otp is " + user.getOtp() + "to verify account ");
				getJavaMailSender().send(message);
				map.put("message", "Your account is created.");

				resp.setStatus(APIConstant.SUCCESS.intValue());
				resp.setMessage("Success");
				resp.setResponse(map);
				}catch(Exception e){
					e.printStackTrace();
				}
				return resp;
			}
			else {
				throw new BadRequest400("User not saved");
			}
			
		} else {
			throw new BadRequest400("User already exists!");
		}
	}
	
	
	
	
	@PostMapping("/verify/otp")
	public Map<String,String> verifyOtp(@RequestBody OtpDto otp){
		System.out.println("1========");
		Map<String, String> map = new HashMap<>();
		//try{
			System.out.println("otp email========"+otp.getEmail());
		User user = userRepo.findFirstByEmail(otp.getEmail());
		System.out.println("user===="+user);
		if(user != null){
			System.out.println("2====");
			if(otp.getOtp() == user.getOtp()){
				System.out.println("true====");
				map.put("message", "otp verified");
			}else{
				throw new BadRequest400("Otp not matched");
			}
			
		}else{
			throw new BadRequest400("user not found ");
		}
		//}catch(Exception e){
		//	e.printStackTrace();
		//}
		return map;
	}

}

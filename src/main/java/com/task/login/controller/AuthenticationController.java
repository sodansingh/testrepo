package com.task.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.login.config.TokenProvider;
import com.task.login.model.APIConstant;
import com.task.login.model.APIResponse;
import com.task.login.model.AuthToken;
import com.task.login.model.Login;
import com.task.login.model.TokenDto;
import com.task.login.model.User;
import com.task.login.repository.UserRepository;



@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	private UserRepository userRepo;
		
	/*@RequestMapping(value = "/token", method = RequestMethod.POST)
    public APIResponse<AuthToken> register(@RequestBody Login login) throws Exception {
    	
		APIResponse<AuthToken> response = new APIResponse<AuthToken>();
		
		try{
       final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(),
                            login.getPassword()
                    )
            );
            
        	 SecurityContextHolder.getContext().setAuthentication(authentication);
        	 final User user = userRepo.findByEmail(login.getEmail());
      		
        	 final String token = jwtTokenUtil.generateToken(authentication);
        	 userRepo.save(user);
        		response.setStatus(APIConstant.SUCCESS.intValue());
      			response.setMessage("Success");
      			response.setResponse(new AuthToken(token));
		}catch(Exception e){
			e.printStackTrace();
		}
            
             	return response;    
    }*/
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
    public APIResponse<TokenDto> register(@RequestBody Login login) throws Exception {
    	
		
		
		//try{
			APIResponse<TokenDto> response = new APIResponse<TokenDto>();
       final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(),
                            login.getPassword()
                    )
            );
            
        	 SecurityContextHolder.getContext().setAuthentication(authentication);
        	 final User user = userRepo.findByEmail(login.getEmail());
        	 
        	 if(user == null){
        		 throw new BadCredentialsException("wrong credentials");  
        	 }
        	 else{
        	 TokenDto tokenDto = new TokenDto();
        	 
        	 
        	 final String token = jwtTokenUtil.generateToken(authentication);
        	 
        	 userRepo.save(user);
        	 tokenDto.setEmail(user.getEmail());
        	 tokenDto.setUserid(user.getUserid());
        	 tokenDto.setToken(token);
        		response.setStatus(APIConstant.SUCCESS.intValue());
      			response.setMessage("Success");
      			response.setResponse(tokenDto);
      			return response; 
        	 }
	//	}catch(Exception e){
	//		throw new BadCredentialsException("wrong credentials");       		
	//	}
            
             	   
    }
}


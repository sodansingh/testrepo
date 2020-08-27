package com.task.login.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.task.login.model.APIConstant;
import com.task.login.model.APIResponse;


/* Exception Handler for different type of exceptions */

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler{
	
	APIResponse<?> response = new APIResponse<>();
	

	@ResponseBody
	@ExceptionHandler(InternalServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public APIResponse<?> AllExceptions(Exception ex) {
		
		 response.setStatus(APIConstant.FAILURE.intValue());
		 response.setMessage(""+ex.getMessage());
		 return response;
	}
	
	
	@ResponseBody
	@ExceptionHandler(BadRequest400.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public APIResponse<?> badRequest400(BadRequest400 ex) {
		
		  response.setStatus(APIConstant.FAILURE.intValue());
		  response.setMessage(ex.getMessage());
		  return response;
	}
	
	
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException .class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public APIResponse<?> handleError404(ResourceNotFoundException  ex) {
		
		  response.setStatus(APIConstant.FAILURE.intValue());
		  response.setMessage(ex.getMessage());		 
		  return response;
	}
	

	@ResponseBody
	@ExceptionHandler(UnAuthorizedUser403.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public APIResponse<?> handle403(UnAuthorizedUser403  ex) {
		
		  response.setStatus(APIConstant.FAILURE.intValue());
		  response.setMessage(ex.getMessage());		 
		  return response;
	}
	
	@ResponseBody
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public APIResponse<?> badCredentials(BadCredentialsException  ex) {
		
		  response.setStatus(APIConstant.FAILURE.intValue());
		  response.setMessage("Oops,username or password is wrong.Please try again!");		 
		  return response;
	}
	
	
	
	@ResponseBody
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public APIResponse<?> accessDeniedException(AccessDeniedException  ex) {
		
		  response.setStatus(APIConstant.FAILURE.intValue());
		  response.setMessage("You are not authorized to perform this operation. Please contact site administrator");		 
		  return response;
	}
	

	@ResponseBody
	@ExceptionHandler(DataNotFoundException204.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public APIResponse<?> DataNotFound204(DataNotFoundException204  ex) {
		
		  response.setStatus(APIConstant.FAILURE.intValue());
		  response.setMessage(ex.getMessage());		 
		  return response;
	}
	
	@ResponseBody
	@ExceptionHandler(UnProcessableEntityException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public APIResponse<?> Unprocessableentity422(UnProcessableEntityException  ex) {
		
		  response.setStatus(APIConstant.FAILURE.intValue());
		  response.setMessage(ex.getMessage());		 
		  return response;
	}

	
	
}

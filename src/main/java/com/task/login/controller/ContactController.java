package com.task.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.login.exceptionhandler.ResourceNotFoundException;
import com.task.login.model.APIConstant;
import com.task.login.model.APIResponse;
import com.task.login.model.Contact;
import com.task.login.repository.ContactRepo;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
	
	
private ContactRepo contactRepo;
	
	
	@Autowired
	public ContactController(ContactRepo contactRepo) {
		this.contactRepo=contactRepo;		
	}
	
	
	@GetMapping("/get/contacts")
	public APIResponse<List<Contact>> getContacts(){
			
		List<Contact> listOfContact = new ArrayList<>();
		listOfContact = contactRepo.getContact();
		System.out.println("listOfContact====="+listOfContact);
		APIResponse<List<Contact>> response = new APIResponse<>();
		
		response.setStatus(APIConstant.SUCCESS.intValue());
		response.setMessage("Success");
		response.setResponse(listOfContact);
		
		return response;		
	}
	
	
	@PostMapping("/add/contact/{userid}")
	public APIResponse<Contact> addContact(@RequestBody Contact contact, @PathVariable int userid){
		
	
		APIResponse<Contact> response = new APIResponse<>();
		contact.setUserid(userid);
		contactRepo.save(contact);
		
		response.setStatus(APIConstant.SUCCESS.intValue());
		response.setMessage("Success");
		response.setResponse(contact);
		
		return response;
	}
	
	@GetMapping("/get/contacts/byuser/{userid}")
	public APIResponse<List<Contact>> getContactByUser(@PathVariable int userid){
			
		List<Contact> listOfContact = new ArrayList<>();
		listOfContact = contactRepo.getContactByUser(userid);
		System.out.println("listOfContact====="+listOfContact);
		APIResponse<List<Contact>> response = new APIResponse<>();
		
		response.setStatus(APIConstant.SUCCESS.intValue());
		response.setMessage("Success");
		response.setResponse(listOfContact);
		
		return response;		
	}
	
	@PostMapping("/edit/contact/{contactid}")
	public APIResponse<Contact> editContact(@RequestBody Contact contact, @PathVariable int contactid){
		
	
		APIResponse<Contact> response = new APIResponse<>();
		//Contact contactObj = new Contact();
		Contact contactEntity = contactRepo.getContactById(contactid);
		
		if(contactEntity != null){
			
			contactEntity.setContactNo(contact.getContactNo());
			contactEntity.setName(contact.getName());
			contactEntity.setOccupation(contact.getOccupation());
			contactEntity.setDob(contact.getDob());
			contactEntity.setDoj(contact.getDoj());
			contactRepo.save(contactEntity);
		}else {
			throw new ResourceNotFoundException("contact not found for given ID.");
		}
		
		response.setStatus(APIConstant.SUCCESS.intValue());
		response.setMessage("Success");
		response.setResponse(contactEntity);
		
		return response;
	}

}

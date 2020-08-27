package com.task.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.task.login.model.Contact;

public interface ContactRepo extends CrudRepository<Contact, Integer> {
	
	@Query("select c from Contact c ")
	List<Contact> getContact();
	
	@Query("select c from Contact c where c.userid=?1")
	List<Contact> getContactByUser(int userid);
	
	@Query("select c from Contact c where c.contactid=?1")
	Contact getContactById(int contactid);
	
}

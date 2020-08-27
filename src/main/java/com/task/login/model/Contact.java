package com.task.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//	@Column(name="contactid")
	private int contactid;
	private int userid;
	@Column(name = "mobileno")
	private String contactNo;
	private String name;
	private String occupation;
	private String dob;
	private String doj;
	
	
	
	
	public int getContactid() {
		return contactid;
	}
	public void setContactid(int contactid) {
		this.contactid = contactid;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Contact [contactid=" + contactid + ", userid=" + userid + ", contactNo=" + contactNo + ", name=" + name
				+ ", occupation=" + occupation + ", dob=" + dob + ", doj=" + doj + "]";
	}
	
	
	
}

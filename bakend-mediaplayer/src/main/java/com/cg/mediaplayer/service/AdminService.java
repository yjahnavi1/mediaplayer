package com.cg.mediaplayer.service;

import java.util.List;

import com.cg.mediaplayer.entity.Admin;
import com.cg.mediaplayer.exception.RecordNotFoundException;



public interface AdminService {
	
	public List<Admin> addAdmin(Admin admin) throws RecordNotFoundException;	
	
	public Admin updateAdmin(Admin admin) throws RecordNotFoundException;
	
	public List<Admin> deleteAdmin(Integer adminLoginId) throws RecordNotFoundException;
	
	public List<Admin> viewAllAdmins() throws RecordNotFoundException;
}

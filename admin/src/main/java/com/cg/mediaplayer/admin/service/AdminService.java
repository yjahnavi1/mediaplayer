package com.cg.mediaplayer.admin.service;

import java.util.List;

import com.cg.mediaplayer.admin.entity.Admin;
import com.cg.mediaplayer.admin.exception.RecordNotFoundException;



public interface AdminService {
	
	public List<Admin> addAdmin(Admin admin) throws RecordNotFoundException;	
	
	public List<Admin> updateAdmin(Admin admin) throws RecordNotFoundException;
	
	public List<Admin> deleteAdmin(Integer adminLoginId) throws RecordNotFoundException;
	
	public List<Admin> viewAllAdmins() throws RecordNotFoundException;
}

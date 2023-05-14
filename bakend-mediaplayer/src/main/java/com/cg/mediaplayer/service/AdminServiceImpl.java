package com.cg.mediaplayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mediaplayer.dao.AdminDao;
import com.cg.mediaplayer.entity.Admin;
import com.cg.mediaplayer.exception.RecordNotFoundException;



@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public List<Admin> addAdmin(Admin admin) throws RecordNotFoundException{
	Admin existingAdmin = adminDao.findByUserName(admin.getUserName());
	if(existingAdmin != null) {
		throw new RecordNotFoundException("Admin already registered!!");
	}
	adminDao.save(admin);
	return viewAllAdmins();
	}

	@Override
	public Admin updateAdmin(Admin admin) throws RecordNotFoundException {
		return adminDao.save(admin);
	}

	@Override
	public List<Admin> deleteAdmin(Integer adminLoginId) throws RecordNotFoundException {
		adminDao.deleteById(adminLoginId);
		return viewAllAdmins();
	  }
	
	@Override
	public List<Admin> viewAllAdmins() throws RecordNotFoundException {
		List<Admin> admins = adminDao.findAll();
		if (admins.isEmpty()) {
			throw new RecordNotFoundException("No admins found!!");
		}
		return admins;
	  }
}
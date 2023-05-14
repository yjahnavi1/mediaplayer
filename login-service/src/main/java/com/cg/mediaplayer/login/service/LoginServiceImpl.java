package com.cg.mediaplayer.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.login.dao.LoginDAO;
import com.cg.mediaplayer.login.entity.Login;
import com.cg.mediaplyer.login.exception.RecordNotFoundException;

@Repository
public class LoginServiceImpl implements LoginService{
	

	@Autowired
	private LoginDAO loginDAO;

	@Override
	public List<Login> getAllLoginRecords() {
		return loginDAO.findAll();
	}

	@Override
	public List<Login> add(Login bean) {
		Login obj = loginDAO.save(bean);
		if(obj !=null) {
			return getAllLoginRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Login> update(Login bean) throws RecordNotFoundException  {
		Login p=loginDAO.save(bean);
		if(p!=null) {
			return getAllLoginRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Login> deleteById(Integer id) throws RecordNotFoundException  {
		loginDAO.deleteById(id);		
		return getAllLoginRecords();
	}

//	@Override
//	public List<Login> deleteByUserName(String userName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Login findLoginRecordById(Integer id) throws RecordNotFoundException {
		Optional<Login> op = loginDAO.findById(id);
		if(op.isPresent()) {
			//logger.info("Login is retrieved from database for LoginID "+id);
			return op.get();
		}
		else
		{
			//logger.error("Login is NOT retrieved from database for LoginID "+id);
			throw new RecordNotFoundException("Login with "+id + " not found");
		}		
	}

	@Override
	public List<Login> findLoginRecordByUserName(String userName) throws RecordNotFoundException  {
		List<Login> list = new ArrayList<Login>();
		List<Login> allLogins = getAllLoginRecords();
		ListIterator<Login> lItr = allLogins.listIterator();
		while(lItr.hasNext()) {
			Login login = lItr.next();
			if(login.getUserName().equals(userName)) {
				list.add(login);
			}
		}
		return list;
	}

	@Override
	public List<Login> save(Login bean) {
		Login p = loginDAO.save(bean);
		if(p!=null) {
			return getAllLoginRecords();
		}
		else {
			return null;
		}
	}


}

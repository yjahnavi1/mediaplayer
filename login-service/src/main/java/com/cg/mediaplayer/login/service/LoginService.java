package com.cg.mediaplayer.login.service;

import java.util.List;


import com.cg.mediaplayer.login.entity.Login;
import com.cg.mediaplyer.login.exception.RecordNotFoundException;


public interface LoginService {
	
	public List<Login> getAllLoginRecords();

	public List<Login> add(Login bean);
	
	public List<Login> update(Login bean) throws RecordNotFoundException ;
	
	public List<Login> deleteById(Integer id) throws RecordNotFoundException ;
	
	//public List<Login> deleteByUserName(String userName);
	
	public Login findLoginRecordById(Integer id) throws RecordNotFoundException ;// throws RecordNotFoundException;
	
	public List<Login> findLoginRecordByUserName(String userName) throws RecordNotFoundException ;

	public List<Login> save(Login bean);

}

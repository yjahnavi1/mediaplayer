package com.cg.mediaplayer.service;

import java.util.List;

import com.cg.mediaplayer.entity.Login;
import com.cg.mediaplayer.exception.RecordNotFoundException;


public interface ILoginService {
	
	public List<Login> getAllLoginRecords();

	public List<Login> add(Login bean);
	
	public List<Login> update(Login bean) throws RecordNotFoundException ;
	
	public List<Login> deleteById(Integer id) throws RecordNotFoundException ;
	
	//public List<Login> deleteByUserName(String userName);
	
	public Login findLoginRecordById(Integer id) throws RecordNotFoundException ;// throws RecordNotFoundException;
	
	public List<Login> findLoginRecordByUserName(String userName) throws RecordNotFoundException ;

	public List<Login> save(Login bean);

}

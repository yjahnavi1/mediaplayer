package com.cg.mediaplayer.service;

import java.util.List;

import com.cg.mediaplayer.entity.User;
import com.cg.mediaplayer.exception.DuplicateRecordException;
import com.cg.mediaplayer.exception.RecordNotFoundException;


public interface IUserService {
	
	public List<User> addUser(User user) throws DuplicateRecordException, RecordNotFoundException;	
	
	public List<User> updateUser(User user) throws DuplicateRecordException, RecordNotFoundException;
	
	public List<User> deleteUser(Integer userLoginId) throws RecordNotFoundException, RecordNotFoundException;
	
	public List<User> viewAllUsers() throws RecordNotFoundException;
	
//	public void subscribeUser(User user) throws UserException;
	
//	public void unsubscribeUser(int userLoginId) throws UserException;
	
	public User getUserByUsername(String username) throws RecordNotFoundException;
	
	public List<User> save(User bean) throws RecordNotFoundException;
	
}

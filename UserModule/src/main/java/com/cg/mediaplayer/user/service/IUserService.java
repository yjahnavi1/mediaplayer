package com.cg.mediaplayer.user.service;

import java.util.List;

import com.cg.mediaplayer.user.entity.User;
import com.cg.mediaplayer.user.exception.DuplicateRecordException;
import com.cg.mediaplayer.user.exception.RecordNotFoundException;


public interface IUserService {
	
	public List<User> addUser(User user) throws DuplicateRecordException, RecordNotFoundException;	
	
	public List<User> updateUser(User user) throws DuplicateRecordException, RecordNotFoundException;
	
	public List<User> deleteUser(Integer userLoginId) throws RecordNotFoundException;
	
	public List<User> viewAllUsers() throws RecordNotFoundException;
	
	public User getUserByUsername(String username) throws RecordNotFoundException;
}

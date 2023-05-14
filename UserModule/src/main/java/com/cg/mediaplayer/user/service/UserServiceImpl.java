package com.cg.mediaplayer.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mediaplayer.user.dao.IUserDao;
import com.cg.mediaplayer.user.entity.User;
import com.cg.mediaplayer.user.exception.DuplicateRecordException;
import com.cg.mediaplayer.user.exception.RecordNotFoundException;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public List<User> addUser(User user) throws DuplicateRecordException,RecordNotFoundException{
	User existingUser = userDao.findByUserName(user.getUserName());
	if(existingUser != null) {
		throw new DuplicateRecordException("User already registered!!");
	}
	userDao.save(user);
	return viewAllUsers();
	}

	@Override
	public List<User> updateUser(User user) throws RecordNotFoundException, DuplicateRecordException {
//		User user1 = userDao.save(user);
//		if(user1.getUserName() != null) {
//			throw new DuplicateRecordException("User updated!!");
//		}
		userDao.save(user);
		return viewAllUsers();
	}

	@Override
	public List<User> deleteUser(Integer userLoginId) throws RecordNotFoundException{
		if(userDao.findById(userLoginId)!= null) {
			userDao.deleteById(userLoginId);
			return viewAllUsers();
		}
		else throw new RecordNotFoundException("User Not Found with ID : "+userLoginId+" to Delete");
		
	  }
	
	@Override
	public List<User> viewAllUsers() throws RecordNotFoundException {
		List<User> users = userDao.findAll();
		if (users.isEmpty()) {
			throw new RecordNotFoundException("No users found!!");
		}
		return users;
	  }
	
	@Override
	public User getUserByUsername(String username) throws RecordNotFoundException {
		User user = userDao.findByUserName(username);
        if (user == null) {
            throw new RecordNotFoundException("Could not find user");
        }
        return user;
    }
}
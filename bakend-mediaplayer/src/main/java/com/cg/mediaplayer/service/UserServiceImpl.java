package com.cg.mediaplayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mediaplayer.dao.IUserDao;
import com.cg.mediaplayer.entity.User;
import com.cg.mediaplayer.exception.DuplicateRecordException;
import com.cg.mediaplayer.exception.RecordNotFoundException;


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
	public List<User> updateUser(User user) throws DuplicateRecordException,RecordNotFoundException {
//		User existingUser = userDao.findByUserName(user.getUserName());
//		if(existingUser != null) {
//			throw new DuplicateRecordException("User already registered!!");
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

//	@Override
//	public void subscribeUser(User user) throws UserException {
//		
//	}

//	@Override
//	public void unsubscribeUser(int userLoginId) throws UserException {
//		// TODO Auto-generated method stub
//		
//	}
//
	@Override
	public User getUserByUsername(String userName) throws RecordNotFoundException {
		User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new RecordNotFoundException("Could not find user");
        }
        return user;
    }

	@Override
	public List<User> save(User bean) throws RecordNotFoundException {
		userDao.save(bean);
		return viewAllUsers();
	}
}
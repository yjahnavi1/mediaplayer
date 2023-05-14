package com.cg.mediaplayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cg.mediaplayer.dao.IUserDao;
import com.cg.mediaplayer.entity.User;
import com.cg.mediaplayer.exception.DuplicateRecordException;
import com.cg.mediaplayer.exception.RecordNotFoundException;
import com.cg.mediaplayer.service.IUserService;
import com.cg.mediaplayer.service.UserServiceImpl;


@SpringBootTest
@ContextConfiguration(classes = MediaplayerApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	
Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Mock
	IUserDao iUserDao;
	
	@InjectMocks
	IUserService service = new UserServiceImpl();
	
	List<User> gList;
	User user;
	
	@BeforeEach
	public void beforeTest() {
		gList = new ArrayList<>();
		user = new User(1,"yarram","jahnavi","yjanu","janu@gmail.com","janu@123");
		gList.add(user);
	}

	@Test
	@Order(1)
	void testinsertUser() throws RecordNotFoundException {
		logger.info("Testing Inserting User");
		
		// When record is inserted then return list with that guard
		when(iUserDao.save(user)).thenReturn(user);
		when(iUserDao.findAll()).thenReturn(gList);
		List<User> l1=service.save(user);
		// service will call UserDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("yjanu",l1.get(0).getUserName());
		
		// verify that UserDAO.save method got executed at least once
		verify(iUserDao,times(1)).save(user);
		verify(iUserDao,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void testupdateUser() throws DuplicateRecordException, RecordNotFoundException {
		logger.info("Testing Update User");
		
		User p = gList.get(0);
		p.setUserName("janii");
		gList.set(0, p);

		// When record is update then return list with updated User
		when(iUserDao.save(user)).thenReturn(p);
		when(iUserDao.findAll()).thenReturn(gList);
		
		List<User> l1=service.updateUser(user);
		// service will call UserDAO update
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("janii",l1.get(0).getUserName());
		
		// verify that UserDAO.update method got executed at least once
		verify(iUserDao,times(1)).save(user);
		verify(iUserDao,times(1)).findAll();
		
	}
	@Test
	@Order(4)
	void testdeleteUser() throws RecordNotFoundException {
		logger.info("Testing Delete User");
		
		gList.remove(user);
		// When record is deleted then return list without that User
		doNothing().when(iUserDao).deleteById(1);
		when(iUserDao.findAll()).thenReturn(gList);
		
		List<User> l1=service.deleteUser(1);
		// service will call UserDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that UserDao.delete method got executed at least once
		verify(iUserDao,times(1)).deleteById(1);
		verify(iUserDao,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindUser() throws RecordNotFoundException {
		logger.info("Testing find User by id"+user.toString());
		
		Optional<User> op=Optional.of(user);
		// When record is findById then return  User of id 1001
		if(op.isPresent()) {
			when(iUserDao.findById(1)).thenReturn(op);
		}
		
		User p=service.getUserByUsername("yjanu");
		//logger.info(p.toString());
		// service will call GuardDAO findById 
		assertNotNull(p);
		assertEquals(1,p.getUserLoginId());
		assertEquals("yarram",p.getFirstName());
		assertThrows(RecordNotFoundException.class, ()->service.getUserByUsername("yarramsetty"));
		
		
		// verify that UserDAO.findById(1001) method got executed at least once
		verify(iUserDao,times(1)).findByUserName("yarramsetty");
		
		
	}

}

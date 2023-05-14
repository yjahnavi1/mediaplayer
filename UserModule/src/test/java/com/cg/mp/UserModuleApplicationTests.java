package com.cg.mp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cg.mediaplayer.user.UserModuleApplication;
import com.cg.mediaplayer.user.dao.IUserDao;
import com.cg.mediaplayer.user.entity.User;
import com.cg.mediaplayer.user.exception.DuplicateRecordException;
import com.cg.mediaplayer.user.exception.RecordNotFoundException;
import com.cg.mediaplayer.user.service.IUserService;
import com.cg.mediaplayer.user.service.UserServiceImpl;
@SpringBootTest
@ContextConfiguration(classes = UserModuleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class UserModuleApplicationTests {
	@Mock
	private IUserDao userDao;
	
	@InjectMocks
	IUserService service = new UserServiceImpl();	
	
	List<User> userlist;
	
	User u;
	
	@BeforeEach
	public void beforeTest() {
		userlist=new ArrayList<>();
		u=new User(1,"Jack","Daniel","jackd2","URL","jack@gmail.com","daniel");
		userlist.add(u);
	}

	@Test
	@Order(1)
	void testaddUser() throws RecordNotFoundException, DuplicateRecordException {
		when(userDao.save(u)).thenReturn(u);
		when(userDao.findAll()).thenReturn(userlist);
		List<User> u1=service.addUser(u);
		assertTrue(u1.size()>0);
		assertEquals(1,u1.size());
		assertEquals("jackd2",u1.get(0).getUserName());
		verify(userDao,times(1)).save(u);
		verify(userDao,times(1)).findAll();
		
	}
	
	@Test
	@Order(2)
	void testupdateUser() throws RecordNotFoundException, DuplicateRecordException {
		User u=userlist.get(0);
		u.setUserName("daniel");
		userlist.set(0, u);
		when(userDao.save(u)).thenReturn(u);
		when(userDao.findAll()).thenReturn(userlist);
		List<User> u1=service.updateUser(u);
		assertTrue(u1.size()>0);
		assertEquals(1,u1.size());
		assertEquals("daniel",u1.get(0).getUserName());
		verify(userDao,times(1)).save(u);
		verify(userDao,times(1)).findAll();
	}


	@Test
	@Order(4)
	void testdeleteUser() throws RecordNotFoundException {
		userlist.remove(u);
		doNothing().when(userDao).deleteById(1);
		when(userDao.findAll()).thenReturn(userlist);
		List<User> u1=service.deleteUser(1);
		assertTrue(u1.size()==0);
		assertEquals(0,u1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->u1.get(0));
		verify(userDao,times(1)).deleteById(1);
		verify(userDao,times(1)).findAll();
		}
	
	@Test
	@Order(3)
	void testGetUserByUsername() throws RecordNotFoundException {
		String username = "jackd2";
		User user = new User(1,"Jack","Daniel","jackd2","URL","jack@gmail.com","daniel");
		when(userDao.findByUserName(username)).thenReturn(user);
		User retrievedUser = service.getUserByUsername(username);
		assertEquals(user, retrievedUser);
		verify(userDao, times(1)).findByUserName(username);
	}
	
	@Test
	@Order(5)
	void testViewAllUsers() throws RecordNotFoundException {
		List<User> userList =  (List<User>) Stream.of(
				new User(1,"Jack","Daniel","jackd2","URL","jack@gmail.com","daniel"), 
				new User(2,"John","Doe","john9","URL","johndoe@gmail.com","john2")).collect
				(Collectors.toList());
		when(userDao.findAll()).thenReturn(userList);
		assertEquals(2, service.viewAllUsers().size());
	}
}

//@Test
//@Order(4)
//void testdeleteUser() throws RecordNotFoundException {
//	User user=new User(1,"Jack","Daniel","jackd2","URL","jack@gmail.com","daniel");
//	userDao.delete(user);
//}

//@Test
//@Order(4)
//void testDeleteUser() throws RecordNotFoundException {
//	userlist.remove(u);
//	//UserDao userDao = mock(UserDao.class);
//	doNothing().when(userDao).deleteById(1);
//    when(userDao.findAll()).thenReturn(userlist);
//    List<User> remainingUsers = service.deleteUser(1);
//    assertTrue(remainingUsers.size() == 0);
//    assertEquals("Jane", remainingUsers.get(0).getUserName());
//    verify(userDao, times(1)).deleteById(1);
//    verify(userDao, times(1)).findAll();
//    assertThrows(RecordNotFoundException.class, () -> service.deleteUser(3));
//}
//@Test
//@Order(4)
//void testdeleteLoginRecord() throws RecordNotFoundException {
//
//  userlist.remove(u);
//  // When record is deleted then return list without that Login
//  doNothing().when(userDao).deleteById(1);
//  when(userDao.findAll()).thenReturn(userlist);
//
//  List<User> u1=service.deleteById(1);
//  assertTrue(u1.size()==0);
//  assertEquals(0,u1.size());
//  assertThrows(IndexOutOfBoundsException.class, ()->u1.get(0));
//
//  // verify that LoginDAO.delete method got executed at least once
//  verify(userDao,times(1)).deleteById(1);
//  verify(userDao,times(1)).findAll();
//
//
//}
//@Test
//@Order(4)
//public void testDeleteUser() throws RecordNotFoundException {
//	Integer userLoginId = 1;
//	when(userDao.deleteById(userLoginId)).thenReturn(true);
//	List<User> userList = service.deleteUser(userLoginId);
//	assertEquals(0, userList.size());
//	verify(userDao, times(1)).deleteById(userLoginId);
//}
//
//@Test
//@Order(3)
//void testfindUser() throws RecordNotFoundException {
//	Optional<User> op=Optional.of(u);
//	// When record is findProduct then return  user of id 1
//	when(userDao.findById(1)).thenReturn(op);
//	
//	User u=service.getUserByUsername("jackd2");
//	// service will call dao findProduct 
//	assertNotNull(u);
//	assertEquals(1,u.getUserLoginId());
//	assertEquals("jackd2",u.getUserName());
//	assertThrows(RecordNotFoundException.class, ()->service.getUserByUsername("jackd22"));
//	
//	// verify that productDao.findProduct(100) method got executed at least once
//	verify(userDao,times(1)).findById(1);
//	
//	
//}

//	
//	@Test
//	@Order(5)
//	void testViewAllRoutes() throws RecordNotFoundException {
//		userDao.findAll();
//	}
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

import com.cg.mediaplayer.dao.LoginDAO;
import com.cg.mediaplayer.entity.Login;
import com.cg.mediaplayer.exception.RecordNotFoundException;
import com.cg.mediaplayer.service.ILoginService;
import com.cg.mediaplayer.service.LoginServiceImpl;

@SpringBootTest
@ContextConfiguration(classes = MediaplayerApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginServiceTest {
	
Logger logger = LoggerFactory.getLogger(LoginServiceTest.class);
	
	@Mock
	LoginDAO loginDAO;
	
	@InjectMocks
	ILoginService service = new LoginServiceImpl();
	
	List<Login> lList;
	Login login;
	
	@BeforeEach
	public void beforeTest() {
		lList = new ArrayList<>();
		login = new Login(1,"yjanu","user","janu@123");
		lList.add(login);
	}

	@Test
	@Order(1)
	void testinsertLoginRecord() {
		logger.info("Testing Inserting LoginRecord");
		
		// When record is inserted then return list with that login
		when(loginDAO.save(login)).thenReturn(login);
		when(loginDAO.findAll()).thenReturn(lList);
		List<Login> l1=service.save(login);
		// service will call loginDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("yjanu",l1.get(0).getUserName());
		
		// verify that loginDAO.save method got executed at least once
		verify(loginDAO,times(1)).save(login);
		verify(loginDAO,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void testupdateLoginRecord() throws RecordNotFoundException {
		logger.info("Testing Update LoginRecord");
		
		Login p = lList.get(0);
		p.setUserName("yjanuu");
		lList.set(0, p);

		// When record is update then return list with updated login
		when(loginDAO.save(login)).thenReturn(p);
		when(loginDAO.findAll()).thenReturn(lList);
		
		List<Login> l1=service.update(login);
		// service will call loginDAO update
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("yjanuu",l1.get(0).getUserName());
		
		// verify that loginDAO.update method got executed at least once
		verify(loginDAO,times(1)).save(login);
		verify(loginDAO,times(1)).findAll();
		
	}
	@Test
	@Order(4)
	void testdeleteLoginRecord() throws RecordNotFoundException {
		logger.info("Testing Delete LoginRecord");
		
		lList.remove(login);
		// When record is deleted then return list without that Login
		doNothing().when(loginDAO).deleteById(1);
		when(loginDAO.findAll()).thenReturn(lList);
		
		List<Login> l1=service.deleteById(1);
		// service will call LoginDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that LoginDAO.delete method got executed at least once
		verify(loginDAO,times(1)).deleteById(1);
		verify(loginDAO,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindLoginRecord() throws RecordNotFoundException {
		logger.info("Testing find LoginRecord by id");
		
		Optional<Login> op=Optional.of(login);
		// When record is findById then return  LoginRecord of id 1001
		if(op.isPresent()) {
			when(loginDAO.findById(1)).thenReturn(op);
		}
		
		Login p=service.findLoginRecordById(1);
		logger.info(p.toString());
		// service will call LoginDAO findById 
		assertNotNull(p);
		assertEquals(1,p.getLoginId());
		assertEquals("yjanu",p.getUserName());
		assertThrows(RecordNotFoundException.class, ()->service.findLoginRecordById(2));
		
		
		// verify that loginDAO.findById(1001) method got executed at least once
		verify(loginDAO,times(1)).findById(1);
		
		
	}

}

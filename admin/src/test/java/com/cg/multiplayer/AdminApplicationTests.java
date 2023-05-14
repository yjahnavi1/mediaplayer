package com.cg.multiplayer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
 
import com.cg.mediaplayer.admin.AdminApplication;
import com.cg.mediaplayer.admin.dao.AdminDao;
import com.cg.mediaplayer.admin.entity.Admin;
import com.cg.mediaplayer.admin.exception.DuplicateRecordException;
import com.cg.mediaplayer.admin.exception.RecordNotFoundException;
import com.cg.mediaplayer.admin.service.AdminService;
import com.cg.mediaplayer.admin.service.AdminServiceImpl;



@SpringBootTest
@ContextConfiguration(classes = AdminApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class AdminApplicationTests {

	@Mock
    private AdminDao adminDao;

    @InjectMocks
    AdminService service = new AdminServiceImpl();    

    List<Admin> userlist;

    Admin a;

    @BeforeEach
    public void beforeTest() {
        userlist=new ArrayList<>();
        a=new Admin(1,"Jack","Daniel","jackd2","URL","jack@gmail.com","daniel");
        userlist.add(a);
    }
 
    @Test
    @Order(1)
    void testaddUser() throws RecordNotFoundException, DuplicateRecordException {
 
        // When record is inserted then return list with that users
        when(adminDao.save(a)).thenReturn(a);
        when(adminDao.findAll()).thenReturn(userlist);
        List<Admin> u1=service.addAdmin(a);
        // service will call dao addUser 
        assertTrue(u1.size()>0);
        assertEquals(1,u1.size());
        assertEquals("jackd2",u1.get(0).getUserName());

        // verify that userDao.addUser method got executed at least once
        verify(adminDao,times(1)).save(a);
        verify(adminDao,times(1)).findAll();

    }

    @Test
    @Order(2)
    void testupdateUser() throws RecordNotFoundException, DuplicateRecordException {
    	Admin a=userlist.get(0);
        a.setUserName("daniel");
        userlist.set(0, a);
 
        // When record is update then return list with updated users
        when(adminDao.save(a)).thenReturn(a);
        when(adminDao.findAll()).thenReturn(userlist);

        List<Admin> u1=service.updateAdmin(a);
        // service will call dao updateUser 
        assertTrue(u1.size()>0);
        assertEquals(1,u1.size());
        assertEquals("daniel",u1.get(0).getUserName());

        // verify that userDao.updateUser method got executed at least once
        verify(adminDao,times(1)).save(a);
        verify(adminDao,times(1)).findAll();

    }
    @Test
    @Order(4)
    void testViewAllAdmins() throws RecordNotFoundException {
        List<Admin> userList =  (List<Admin>) Stream.of(
                new Admin(1,"Jack","Daniel","jackd2","URL","jack@gmail.com","daniel"), 
                new Admin(2,"John","Doe","john9","URL","johndoe@gmail.com","john2")).collect
                (Collectors.toList());
        when(adminDao.findAll()).thenReturn(userList);
        assertEquals(2, service.viewAllAdmins().size());
    }

}
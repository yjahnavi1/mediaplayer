package com.cg.mediaplayer.category;


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

import com.cg.mediaplayer.category.dao.CategoryDao;
import com.cg.mediaplayer.category.entity.Category;
import com.cg.mediaplayer.category.exception.RecordNotFoundException;
import com.cg.mediaplayer.category.service.CategoryService;
import com.cg.mediaplayer.category.service.CategoryServiceImpl;
 

 
 
 
@SpringBootTest
@ContextConfiguration(classes = Mediaplayer1Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 
class Mediaplayer1ApplicationTests {
 
@Mock
private CategoryDao categoryDao;
 
@InjectMocks
CategoryService service = new CategoryServiceImpl();
 
List<Category> categorylist;
 
Category a;
 
@BeforeEach
public void beforeTest() {
	categorylist=new ArrayList<>();
a=new Category(1,"Sports");
categorylist.add(a);
}
 
@Test
@Order(1)
void testcreateCategory() throws RecordNotFoundException {
 
// When record is inserted then return list with that users
when(categoryDao.save(a)).thenReturn(a);
when(categoryDao.findAll()).thenReturn(categorylist);
List<Category> u1=service.createCategory(a);
// service will call dao addUser
assertTrue(u1.size()>0);
assertEquals(1,u1.size());
assertEquals("Sports",u1.get(0).getCategoryName());
 
// verify that userDao.addUser method got executed at least once
verify(categoryDao,times(1)).save(a);
verify(categoryDao,times(1)).findAll();

}
 
@Test
@Order(2)
void testupdateCategory() throws RecordNotFoundException {
	Category a=categorylist.get(0);
a.setCategoryName("Sports");
categorylist.set(0, a);
 
// When record is update then return list with updated users
when(categoryDao.save(a)).thenReturn(a);
when(categoryDao.findAll()).thenReturn(categorylist);
 
List<Category> u1= service.updateCategory(a);
// service will call dao updateUser
assertTrue(u1.size()>0);
assertEquals(1,u1.size());
assertEquals("Sports",u1.get(0).getCategoryName());
 
// verify that userDao.updateUser method got executed at least once
verify(categoryDao,times(1)).save(a);
verify(categoryDao,times(1)).findAll();
 
}
@Test
@Order(3)
void testDeleteCategory() throws RecordNotFoundException {
	Category category = new Category(1,"Sports");
categoryDao.delete(category);
}
 void testdeleteCategory() throws RecordNotFoundException {
 categorylist.remove(a);
// // When record is deleted then return list without that users
 doNothing().when(categoryDao).deleteById(1);
 when(categoryDao.findAll()).thenReturn(categorylist);

 List<Category> u1=service.deleteCategory(1);
// // service will call dao deleteUser
 assertTrue(u1.size()==0);
 assertEquals(0,u1.size());
 assertThrows(IndexOutOfBoundsException.class, ()->u1.get(0));

 // verify that userDao.deleteUser method got executed at least once
 verify(categoryDao,times(1)).deleteById(6);
 verify(categoryDao,times(1)).findAll();


 }
 
// @Test
// @Order(3)
// void testfindCategoryName() throws RecordNotFoundException {
// Optional<Category> op=Optional.of(a);
// // When record is findProduct then return user of id 1
// when(categoryDao.findById(1)).thenReturn(op);
//
// Category u=service.getFindByCategoryName("Sports");
// // service will call dao findProduct
// assertNotNull(u);
// assertEquals(1,u.getAdminLoginId());
// assertEquals("jackd2",u.getUserName());
// assertThrows(RecordNotFoundException.class, ()->service.getAdminByAdminname("jackd22"));
//
// // verify that productDao.findProduct(100) method got executed at least once
// verify(adminDao,times(1)).findById(1);
//
//
// }

 @Test

 @Order(4)

 void testViewAllCategories() throws RecordNotFoundException {

 List<Category> categoryList = (List<Category>) Stream.of(

 new Category(1,"Sports"),

 new Category(2,"Music")).collect

 (Collectors.toList());

 when(categoryDao.findAll()).thenReturn(categoryList);

 assertEquals(2, service.viewAllCategories().size());

 }
 
 
}
 


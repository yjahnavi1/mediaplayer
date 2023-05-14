package com.cg.mediaplayer.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.mediaplayer.admin.entity.Admin;
import com.cg.mediaplayer.admin.exception.RecordNotFoundException;
import com.cg.mediaplayer.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
//	@Autowired
//	private UserClient userClient;
//	
//	@PostMapping("/addUserRecord")
//	public List<User> addUser(@RequestBody User user) throws DuplicateRecordException,RecordNotFoundException{
//		
//		return this.userClient.addUser(user);
//	}
//	
//	@PutMapping("/updateUserRecord")
//	public List<User> updateUser(@RequestBody User user) throws DuplicateRecordException,RecordNotFoundException {
//		return userClient.updateUser(user);
//		}
//	  
//	@DeleteMapping("/deleteUserRecord/{userLoginId}")
//	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer userLoginId) throws RecordNotFoundException {
//		this.userClient.deleteUser(userLoginId);
//		return new ResponseEntity<>(HttpStatus.OK);
//	  }
//	
//	@GetMapping("/viewAllUsers")
//	public List<User> viewAllUsers() throws RecordNotFoundException {
//		return this.userClient.viewAllUsers();
//	  }
//	
//	@GetMapping("/getUserByUsername")
//	public User getUserByUsername(@PathVariable String username) throws RecordNotFoundException {
//		return this.userClient.getUserByUsername(username);
//	}
	
	@PostMapping("/addAdmin")
	public List<Admin> addAdmin(@RequestBody Admin admin) throws RecordNotFoundException{
		return this.adminService.addAdmin(admin);
	}
	
	@PutMapping("/updateAdmin")
	public List<Admin> updateAdmin(@RequestBody Admin admin) throws RecordNotFoundException {
		return this.adminService.updateAdmin(admin);
		}
	  
	@DeleteMapping("/deleteAdminById/{adminLoginId}")
	public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Integer adminLoginId) throws RecordNotFoundException {
		this.adminService.deleteAdmin(adminLoginId);
		return new ResponseEntity<>(HttpStatus.OK);
	  }
	
	@GetMapping("/viewAllAdmins")
	public List<Admin> viewAllAdmins() throws RecordNotFoundException {
		return this.adminService.viewAllAdmins();
	  }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
	public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {
	 //logger.error(ex.getMessage());
	 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

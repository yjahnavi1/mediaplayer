package com.cg.mediaplayer.controller;

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

import com.cg.mediaplayer.entity.Admin;
import com.cg.mediaplayer.exception.RecordNotFoundException;
import com.cg.mediaplayer.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	
	@PostMapping("/addAdmin")
	public List<Admin> addAdmin(@RequestBody Admin admin) throws RecordNotFoundException{
		return this.adminService.addAdmin(admin);
	}
	
	@PutMapping("/updateAdmin")
	public Admin updateAdmin(@RequestBody Admin admin) throws RecordNotFoundException {
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

package com.cg.mediaplayer.login.controller;

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

import com.cg.mediaplayer.login.entity.Login;
import com.cg.mediaplayer.login.entity.User;
import com.cg.mediaplayer.login.exception.RecordNotFoundException;
//import com.cg.mediaplayer.login.feignclient.UserClient;
import com.cg.mediaplayer.login.service.ILoginService;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins="http://localhost:4200")
public class LoginController {
	
	@Autowired
	private ILoginService iLoginService;
//	
//	@Autowired
//	private UserClient userClient;
	
			// http://localhost:8081/login/allLogins
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@GetMapping("/getAllLoginRecords") //@RequestMapping(value = "/logins",method = RequestMethod.GET)
			public ResponseEntity<List<Login>> getAllLoginRecords(){
				List<Login> logins= iLoginService.getAllLoginRecords();
				if(logins.isEmpty()) {
					return new ResponseEntity("Sorry! LoginRecords not available!", 
							HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<List<Login>>(logins, HttpStatus.OK);
			}
			
			// http://localhost:8081/login/logins 
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@PostMapping("/insertLoginRecord")
			//@RequestMapping(value ="/logins", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
			public ResponseEntity<List<Login>> insertLoginRecord(
				@RequestBody Login bean){ // @RequetBody translate incoming JSON LoginRecord data into Java Guard object
				List<Login> logins= iLoginService.save(bean);
				if(logins.isEmpty())
				{
					return new ResponseEntity("Sorry! LoginRecords not available/unable to insert!", 
							HttpStatus.NOT_FOUND);
				}
				//logger.info("LoginRecord is successfully added");
				return new ResponseEntity<List<Login>>(logins, HttpStatus.OK);
			}	
			
			//http://localhost:8081/login/logins 
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@PutMapping("/updateLoginRecord")
			public ResponseEntity<List<Login>> updateLoginRecord(
					@RequestBody Login bean) throws RecordNotFoundException {
				List<Login> logins= iLoginService.update(bean) ;
				if(logins.isEmpty())
				{
					//logger.error("LoginRecords are not available");
					return new ResponseEntity("Sorry! LoginRecords not available!", 
							HttpStatus.NOT_FOUND);
				}
				//logger.info("LoginRecord is successfully updated");
				return new ResponseEntity<List<Login>>(logins, HttpStatus.OK);
			}
			
			//http://localhost:8081/login/logins/1008 
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@DeleteMapping("/deleteLoginRecordById/{id}")
			public ResponseEntity<List<Login>> deleteLoginRecord(
					@PathVariable("id")Integer id) throws RecordNotFoundException {
				List<Login> logins= iLoginService.deleteById(id);
				if(logins.isEmpty() || logins==null) {
					return new ResponseEntity("Sorry! LoginRecordId is not available!", 
							HttpStatus.NOT_FOUND);
				}
				//logger.info("LoginRecord is successfully deleted");
				return new ResponseEntity<List<Login>>(logins, HttpStatus.OK);
			}
			
			
//			@GetMapping("/viewAllUsers")
//			public List<User> viewAllUsers() throws RecordNotFoundException{
//				return userClient.viewAllUsers();
//			}
			
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
			public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {
			 //logger.error(ex.getMessage());
			 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			
//			@SuppressWarnings({ "rawtypes", "unchecked" })
//			@ExceptionHandler(DuplicateRecordException.class) // local to the RestController
//			public final ResponseEntity<String> DuplicateRecordException(Exception ex, WebRequest request) {
//			 //logger.error(ex.getMessage());
//			 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//			}

}

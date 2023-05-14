package com.cg.mediaplayer.user.controller;

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

import com.cg.mediaplayer.user.entity.User;
import com.cg.mediaplayer.user.exception.DuplicateRecordException;
import com.cg.mediaplayer.user.exception.RecordNotFoundException;
import com.cg.mediaplayer.user.service.IUserService;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/addUserRecord")
	public List<User> addUser(@RequestBody User user) throws DuplicateRecordException,RecordNotFoundException{
		return this.userService.addUser(user);
	}
	
	@PutMapping("/updateUserRecord")
	public List<User> updateUser(@RequestBody User user) throws DuplicateRecordException,RecordNotFoundException {
		return this.userService.updateUser(user);
		}
	  
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @DeleteMapping("/deleteUserRecord/{userLoginId}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable("userLoginId") Integer userLoginId) throws RecordNotFoundException {

        List<User> users= this.userService.deleteUser(userLoginId);
        if(users.isEmpty() || users==null) {
            return new ResponseEntity("Sorry! User with "+userLoginId +" is not available!", 
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
      }

    @GetMapping("/viewAllUsers")
    public List<User> viewAllUsers() throws RecordNotFoundException {
        return this.userService.viewAllUsers();
      }

    @GetMapping("/getUserByUsername/{userName}")
    public User getUserByUsername(@PathVariable("userName") String userName) throws RecordNotFoundException {
        return this.userService.getUserByUsername(userName);
    }

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
	public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {
	 //logger.error(ex.getMessage());
	 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(DuplicateRecordException.class) // local to the RestController
	public final ResponseEntity<String> DuplicateRecordException(Exception ex, WebRequest request) {
	 //logger.error(ex.getMessage());
	 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
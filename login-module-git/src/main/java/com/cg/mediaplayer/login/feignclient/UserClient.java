//package com.cg.mediaplayer.login.feignclient;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.cg.mediaplayer.login.entity.User;
//import com.cg.mediaplayer.login.exception.DuplicateRecordException;
//import com.cg.mediaplayer.login.exception.RecordNotFoundException;
//
//@FeignClient(name="user-service",url="http://localhost:8083/user-app/api")
//public interface UserClient {
//	
//	@PostMapping("/addUserRecord")
//	public List<User> addUser(@RequestBody User user) throws DuplicateRecordException,RecordNotFoundException;
//
//	@GetMapping("/viewAllUsers")
//	public List<User> viewAllUsers() throws RecordNotFoundException ;
//}

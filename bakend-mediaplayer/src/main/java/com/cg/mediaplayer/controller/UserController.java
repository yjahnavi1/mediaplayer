package com.cg.mediaplayer.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.mediaplayer.entity.Login;
import com.cg.mediaplayer.entity.User;
import com.cg.mediaplayer.exception.RecordNotFoundException;
import com.cg.mediaplayer.service.ILoginService;
import com.cg.mediaplayer.service.IUserService;
import com.cg.mediaplayer.exception.DuplicateRecordException;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginService iLoginService;
	
//	@Autowired
//	private LoginClient loginClient;
//	
//	@Autowired
//	private VideoClient videoClient;
//	
	@PostMapping("/addUserRecord")
	public List<User> addUser(@RequestBody User user) throws DuplicateRecordException,RecordNotFoundException{
		Login login = new Login();
		login.setUserName(user.getUserName());
		login.setPassword(user.getPassword());
		login.setRole("user");
		
		iLoginService.add(login);
//		loginClient.insertLoginRecord(login);
		
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
		//logger.info("LoginRecord is successfully deleted");
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
	
	
	
//	/** videos crud*****/
//	
//	
//	@PutMapping("/updateVideoDetails")
//	public ResponseEntity<List<Videos>> updateVideo(
//			@RequestBody Videos bean) throws RecordNotFoundException{
//		return videoClient.updateVideo(bean);
//	}
//	
//
//	
//	// http://localhost:8084/videos/insertVideoDetails
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@PostMapping("/insertVideoDetails")
//	//@RequestMapping(value ="/videos", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
//	public ResponseEntity<List<Videos>> insertVideo(
//		@RequestBody Videos bean) throws RecordNotFoundException{ // @RequetBody translate incoming JSON Videos data into Java Videos object
//		return videoClient.insertVideo(bean);
//	}	
//	
//	
//	//http://localhost:8084/videos/deleteVideoDetailsById/1008 
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@DeleteMapping("/deleteVideoDetailsById/{videoId}")
//	public ResponseEntity<List<Videos>> deleteVideo(
//			@PathVariable("videoId")Integer videoId) throws RecordNotFoundException{
//		return videoClient.deleteVideo(videoId);
//	}
//	
//	//http://localhost:8084/videos/findVideoDetailsById/101 
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@GetMapping("/findVideoDetailsById/{videoId}")
//	public ResponseEntity<Videos> findById(
//			@PathVariable("videoId")Integer videoId) throws RecordNotFoundException{
//		return videoClient.findById(videoId);
//	}
//	// http://localhost:8084/videos/viewAllVideosDetails
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@GetMapping("/viewAllVideosDetails") //@RequestMapping(value = "/videos",method = RequestMethod.GET)
//	public ResponseEntity<List<Videos>> viewAllVideos() throws RecordNotFoundException{
//		return videoClient.viewAllVideos();
//	}
//	
//	
//	/* =======Video Files related APIs====*/
//	
//	@PostMapping("/uploadVideoFile")
//    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
//		return videoClient.upload(file);
//    }
//	
//
//    @GetMapping("/getAllVideoFiles")
//    public List<FileResponse> list() {
//    	return videoClient.list();
//    }
//    
//    @GetMapping("/getFileURL")
//    public String fileResponse(FileEntity fileEntity) {
//    	return videoClient.fileResponse(fileEntity);
//    }
//
//
//    @GetMapping("/getVideoFileById/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//    	return videoClient.getFile(id);
//    }
//    
//    @DeleteMapping("/deleteVideoFileById/{id}")
//    public ResponseEntity<String> deleteById(@PathVariable String id){
//    	return videoClient.deleteById(id);
//    }
//    
//    
//    
//    /**====== Tags APIs ============
//     * @throws RecordNotFoundException */
//    
//  //http://localhost:8084/videos/updateTag 
//	//@SuppressWarnings({ "unchecked", "rawtypes" })
//	@PutMapping("/updateTag")
//	public ResponseEntity<List<Tags>> updateTag(
//			@RequestBody Tags bean) throws RecordNotFoundException{
//		return videoClient.updateTag(bean);
//	}
//	
//
//	
//	// http://localhost:8084/videos/insertTag 
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@PostMapping("/insertTag")
//	//@RequestMapping(value ="/tags", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
//	public ResponseEntity<List<Tags>> insertTag(
//		@RequestBody Tags bean){ // @RequetBody translate incoming JSON guard data into Java Guard object
//		return videoClient.insertTag(bean);
//	}	
//	
//	
//	//http://localhost:8084/videos/deleteTag/1008 
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@DeleteMapping("/deleteTag/{id}")
//	public ResponseEntity<List<Tags>> deleteTag(
//			@PathVariable("id")Integer id) throws RecordNotFoundException{
//		return videoClient.deleteTag(id);
//	}
//	
//	//http://localhost:8084/videos/findTagById/101 
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@GetMapping("/findTagById/{guardId}")
//	public ResponseEntity<Tags> findTagById(
//			@PathVariable("guardId")Integer  id) throws RecordNotFoundException{
//		return videoClient.findTagById(id);
//	}
//	// http://localhost:8084/getAllTags/guards
//	//@SuppressWarnings({ "rawtypes", "unchecked" })
//	@GetMapping("/getAllTags") //@RequestMapping(value = "/tags",method = RequestMethod.GET)
//	public ResponseEntity<List<Tags>> getAllTags(){
//		return videoClient.getAllTags();
//	}
	
}
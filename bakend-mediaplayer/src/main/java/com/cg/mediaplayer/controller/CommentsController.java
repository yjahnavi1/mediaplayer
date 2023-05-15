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

import com.cg.mediaplayer.entity.Comments;
import com.cg.mediaplayer.exception.RecordNotFoundException;
import com.cg.mediaplayer.service.ICommentsService;


@RestController
@RequestMapping("/comments")
@CrossOrigin(origins="http://localhost:4200")
public class CommentsController {
	
	@Autowired
	private ICommentsService commentsService;
	
	@PostMapping("/addComment")
	public List<Comments> addComment(@RequestBody Comments comment) throws RecordNotFoundException{
		return this.commentsService.addComment(comment);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/deleteComment/{commentId}")
	public ResponseEntity<List<Comments>> deleteComment(@PathVariable Integer commentId) throws RecordNotFoundException {
		List<Comments> comments= commentsService.deleteComment(commentId);
		if(comments.isEmpty() || comments==null) {
			return new ResponseEntity("Sorry! commentId not available!", 
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Comments>>(comments, HttpStatus.OK);
	  }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/updateComments")
	public ResponseEntity<List<Comments>> updateComments(
			@RequestBody Comments bean) throws RecordNotFoundException{
		List<Comments> comments= commentsService.update(bean);
		if(comments.isEmpty())
		{
			//logger.error("Comments are not available");
			return new ResponseEntity("Sorry! Comments not there!", 
					HttpStatus.NOT_FOUND);
		}
		//logger.info("CommentsRecord is successfully updated");
		return new ResponseEntity<List<Comments>>(comments, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllComments")
	public List<Comments> getAllComments() throws RecordNotFoundException {
		return this.commentsService.getAllComments();
	  }

	@GetMapping("/increseLikeCount")
	public Integer increaseLikeCount(@PathVariable Integer commentId) throws RecordNotFoundException{
		commentsService.increseLikeCount(commentId);
		return commentsService.getLikeCount(commentId);
	}
	
	@GetMapping("/decreseLikeCount")
	public Integer decreaseLikeCount(@PathVariable Integer commentId) throws RecordNotFoundException{
		commentsService.decreseLikeCount(commentId);
		return commentsService.getLikeCount(commentId);
	}
	
	@GetMapping("/increseDislikeCount")
	public Integer increaseDislikeCount(@PathVariable Integer commentId) throws RecordNotFoundException{
		commentsService.increseDislikeCount(commentId);
		return commentsService.getDislikeCount(commentId);
	}
	
	@GetMapping("/decreseDislikeCount")
	public Integer decreaseDisikeCount(@PathVariable Integer commentId) throws RecordNotFoundException{
		commentsService.decreseDislikeCount(commentId);
		return commentsService.getDislikeCount(commentId);
	}
	
	@GetMapping("/getLikeCount")
	public int getLikeCount(@PathVariable int commentId) throws RecordNotFoundException{
		return this.getLikeCount(commentId);
	}
	
	@GetMapping("/getDislikeCount")
	public int getDislikeCount(@PathVariable int commentId) throws RecordNotFoundException{
		return this.getDislikeCount(commentId);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
	public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {
	 //logger.error(ex.getMessage());
	 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

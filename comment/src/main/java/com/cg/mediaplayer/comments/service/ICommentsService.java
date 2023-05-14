package com.cg.mediaplayer.comments.service;

import java.util.List;

import com.cg.mediaplayer.comments.entity.Comments;
import com.cg.mediaplayer.comments.exception.RecordNotFoundException;

public interface ICommentsService {
	
	public List<Comments> addComment(Comments comments) throws RecordNotFoundException;	
	
	public List<Comments> deleteComment(Integer commentId) throws RecordNotFoundException;
	
	List<Comments> getAllComments() throws RecordNotFoundException;
	
	public void increseLikeCount(Integer commentId) throws RecordNotFoundException;
	
	public void increseDislikeCount(Integer commentId) throws RecordNotFoundException;
	
	public void decreseLikeCount(Integer commentId) throws RecordNotFoundException;
	
	public void decreseDislikeCount(Integer commentId) throws RecordNotFoundException;
	
	public Integer getLikeCount(Integer commentId) throws RecordNotFoundException;

	public Integer getDislikeCount(Integer commentId) throws RecordNotFoundException;
	List<Comments> update(Comments bean) throws RecordNotFoundException;
}

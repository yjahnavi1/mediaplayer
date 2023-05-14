package com.cg.mediaplayer.comments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mediaplayer.comments.dao.CommentsDao;
import com.cg.mediaplayer.comments.entity.Comments;
import com.cg.mediaplayer.comments.exception.RecordNotFoundException;

@Service
public class CommentsServiceImpl implements ICommentsService {

	@Autowired
	private CommentsDao commentsDao;

	@Override
	public List<Comments> addComment(Comments comment) throws RecordNotFoundException {
		commentsDao.save(comment);
		return getAllComments();
	}

	@Override
	public List<Comments> deleteComment(Integer id) throws RecordNotFoundException {
		commentsDao.deleteById(id);
		return getAllComments();
	}
	

	@Override
	public List<Comments> getAllComments() throws RecordNotFoundException {
		List<Comments> comment = commentsDao.findAll();
		if (comment.isEmpty()) {
			throw new RecordNotFoundException("No comment found!!");
		}
		return comment;
	}

	@Override
	public void increseLikeCount(Integer commentId) throws RecordNotFoundException {
		int likeCount = commentsDao.findById(commentId).get().getLikeCount();
		likeCount++;

	}

	@Override
	public void increseDislikeCount(Integer commentId) throws RecordNotFoundException {
		int disLikeCount = commentsDao.findById(commentId).get().getLikeCount();
		disLikeCount++;

	}

	@Override
	public void decreseLikeCount(Integer commentId) throws RecordNotFoundException {
		int likeCount = commentsDao.findById(commentId).get().getLikeCount();
		likeCount--;

	}

	@Override
	public void decreseDislikeCount(Integer commentId) throws RecordNotFoundException {
		int disLikeCount = commentsDao.findById(commentId).get().getLikeCount();
		disLikeCount--;

	}

	@Override
	public Integer getLikeCount(Integer commentId) throws RecordNotFoundException {
		int likeCount = commentsDao.findById(commentId).get().getLikeCount();
		return likeCount;
	}

	@Override
	public Integer getDislikeCount(Integer commentId) throws RecordNotFoundException {
		int disLikeCount = commentsDao.findById(commentId).get().getLikeCount();
		return disLikeCount;
	}

	@Override
	public List<Comments> update(Comments bean) throws RecordNotFoundException {
	Comments p = commentsDao.save(bean);
	if(p!=null) {
	return getAllComments();
	}
	else {
		return null;
	}

		   }

}

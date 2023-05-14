package com.cg.mediaplayer.comments.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.comments.entity.Comments;

@Repository
public interface CommentsDao extends JpaRepository<Comments, Integer>{
	public Comments findByCommenterName(String commenterName);

}

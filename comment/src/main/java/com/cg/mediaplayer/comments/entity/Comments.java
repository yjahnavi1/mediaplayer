package com.cg.mediaplayer.comments.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;
	
	@NotNull(message = "Text cannot be null!")
	@NotBlank(message = "Text connot be blank!")
	private String text;
	
	@NotNull(message = "Commenter Name cannot be null!")
	@NotBlank(message = "Commenter Name connot be blank!")
	private String commenterName;
	
	private Integer likeCount=0;
	
    private Integer disLikeCount=0;
    
    public Comments(){
    	
    }

	public Comments(int commentId,
			@NotNull(message = "Text cannot be null!") @NotBlank(message = "Text connot be blank!") String text,
			@NotNull(message = "Commenter Name cannot be null!") @NotBlank(message = "Commenter Name connot be blank!") String commenterName,
			Integer likeCount, Integer disLikeCount) {
		super();
		this.commentId = commentId;
		this.text = text;
		this.commenterName = commenterName;
		this.likeCount = likeCount;
		this.disLikeCount = disLikeCount;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	public Integer getLikeCount() {
		return likeCount++;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getDisLikeCount() {
		return disLikeCount++;
	}

	public void setDisLikeCount(Integer disLikeCount) {
		this.disLikeCount = disLikeCount;
	}

	public void setId(int i) {
		// TODO Auto-generated method stub
		
	}

	
}
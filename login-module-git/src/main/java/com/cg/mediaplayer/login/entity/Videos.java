package com.cg.mediaplayer.login.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Videos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;

//	@NotNull(message = "Title cannot be null!")
//	@NotBlank(message = "Title connot be blank!")
	private String title;
	
	private String videoName;
	
	@Column(name = "doc_txt", columnDefinition = "text")
	private String videoUrl;

//	@NotNull(message = "Description cannot be null!")
//	@NotBlank(message = "Description connot be blank!")
	private String description;

	private Integer likes;

	private Integer dislikes;

//	@NotNull(message = "VideoUrl cannot be null!")
//	@NotBlank(message = "Video Url connot be blank!")
//	private String videoUrl;

//	private Integer viewCount;

//	@NotNull(message = "Thumbnail Url cannot be null!")
//	@NotBlank(message = "Thumbnail Url connot be blank!")
//	private String thumbnailUrl;

	private LocalDate uploadDate;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id")
//	private FileEntity fileEntity;//FileEntity id
	
	//@OneToMany
	private String tags;
	
	public Videos() {
		super();
	}

	public Videos(int videoId, String title, String videoName, String videoUrl, String description, Integer likes,
			Integer dislikes, LocalDate uploadDate, String tags) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.uploadDate = uploadDate;
		this.tags = tags;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Videos [videoId=" + videoId + ", title=" + title + ", videoName=" + videoName + ", videoUrl=" + videoUrl
				+ ", description=" + description + ", likes=" + likes + ", dislikes=" + dislikes + ", uploadDate="
				+ uploadDate + ", tags=" + tags + "]";
	}

	
	

}

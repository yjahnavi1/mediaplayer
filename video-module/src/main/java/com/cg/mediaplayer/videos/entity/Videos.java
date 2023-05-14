package com.cg.mediaplayer.videos.entity;



import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

//	@NotNull(message = "Description cannot be null!")
//	@NotBlank(message = "Description connot be blank!")
	private String description;

	private Integer likes;

	private Integer dislikes;

//	@NotNull(message = "VideoUrl cannot be null!")
//	@NotBlank(message = "Video Url connot be blank!")
//	private String videoUrl;

	private Integer viewCount;

//	@NotNull(message = "Thumbnail Url cannot be null!")
//	@NotBlank(message = "Thumbnail Url connot be blank!")
	private String thumbnailUrl;

	private LocalDate uploadDate;

//	@OneToOne
//	private FileEntity videoFile;
	
	@OneToMany
	private Set<Tags> tags;
	
	public Videos() {
		super();
	}

	public Videos(int videoId, String title, String description, Integer likes, Integer dislikes, Integer viewCount,
			String thumbnailUrl, LocalDate uploadDate) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.viewCount = viewCount;
		this.thumbnailUrl = thumbnailUrl;
		this.uploadDate = uploadDate;
	}
	
	

	public Videos(int videoId, String title, String description, Integer likes, Integer dislikes, Integer viewCount,
			String thumbnailUrl, LocalDate uploadDate, Set<Tags> tags) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.viewCount = viewCount;
		this.thumbnailUrl = thumbnailUrl;
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

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	

	public Set<Tags> getTags() {
		return tags;
	}

	public void setTags(Set<Tags> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Videos [videoId=" + videoId + ", title=" + title + ", description=" + description + ", likes=" + likes
				+ ", dislikes=" + dislikes + ", viewCount=" + viewCount + ", thumbnailUrl=" + thumbnailUrl
				+ ", uploadDate=" + uploadDate + ", tags=" + tags + "]";
	}
	
	

}

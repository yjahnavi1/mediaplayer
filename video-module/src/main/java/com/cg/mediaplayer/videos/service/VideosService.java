package com.cg.mediaplayer.videos.service;

import java.util.List;

import com.cg.mediaplayer.videos.entity.Videos;
import com.cg.mediaplayer.videos.exception.RecordNotFoundException;

public interface VideosService {
	
	    public List<Videos> addVideos(Videos videos) throws RecordNotFoundException;	
		public List<Videos> editVideos(Videos videos) throws RecordNotFoundException;
		public List<Videos> deleteVideos(Integer videoId) throws RecordNotFoundException;
		public List<Videos> viewAllVideos() throws RecordNotFoundException;
		public void updateThumbnail() throws RecordNotFoundException;
		public Videos getVideoByTitle(String title) throws RecordNotFoundException;
		public Videos getVideoDetails(Integer videoId) throws RecordNotFoundException;
		public void increaseVideoViewCount(Integer videoId) throws RecordNotFoundException;
		public void likeVideo(Integer videoId) throws RecordNotFoundException;
		public void dislikeVideo(Integer videoId) throws RecordNotFoundException;
		public List<Videos> save(Videos bean) throws RecordNotFoundException;

}

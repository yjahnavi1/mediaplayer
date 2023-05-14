package com.cg.mediaplayer.videos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mediaplayer.videos.dao.VideosDao;
import com.cg.mediaplayer.videos.entity.Videos;
import com.cg.mediaplayer.videos.exception.RecordNotFoundException;

@Service
public class VideosServiceImpl implements VideosService{
	
	@Autowired
	private VideosDao videosDao;

	@Override
	public List<Videos> addVideos(Videos videos) throws RecordNotFoundException {
		Videos existingVideos = getVideoByTitle(videos.getTitle());
		if(existingVideos != null) {
			throw new RecordNotFoundException("Videos already exists!");
		}
		videosDao.save(videos);
		return viewAllVideos();
	}

	@Override
	public List<Videos> editVideos(Videos videos) throws RecordNotFoundException {
		Videos existingVideos = videosDao.findByTitle(videos.getTitle());
		if(existingVideos != null) {
			throw new RecordNotFoundException("Videos already exists!");
		}
		videosDao.save(videos);
		return viewAllVideos();
	}

	@Override
	public List<Videos> deleteVideos(Integer videoId) throws RecordNotFoundException {
		videosDao.deleteById(videoId);		
		return viewAllVideos();
	}

	@Override
	public List<Videos> viewAllVideos() throws RecordNotFoundException {
		return videosDao.findAll();
	}

	@Override
	public void updateThumbnail() throws RecordNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Videos getVideoByTitle(String title) throws RecordNotFoundException {
		return videosDao.findByTitle(title);
		
	}

	@Override
	public Videos getVideoDetails(Integer videoId) throws RecordNotFoundException {
		return videosDao.findById(videoId).get();
	}

	@Override
	public void increaseVideoViewCount(Integer videoId) throws RecordNotFoundException {
		
		
	}

	@Override
	public void likeVideo(Integer videoId) throws RecordNotFoundException {
		Integer i = videosDao.findById(videoId).get().getLikes();
		i++;
	}

	@Override
	public void dislikeVideo(Integer videoId) throws RecordNotFoundException {
		Integer i = videosDao.findById(videoId).get().getLikes();
		i++;
		
	}

	@Override
	public List<Videos> save(Videos bean) throws RecordNotFoundException {
		videosDao.save(bean);
		return videosDao.findAll();
		
	}

}

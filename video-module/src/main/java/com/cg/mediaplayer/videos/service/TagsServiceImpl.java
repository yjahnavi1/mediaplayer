package com.cg.mediaplayer.videos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mediaplayer.videos.dao.TagsDao;
import com.cg.mediaplayer.videos.entity.Tags;
import com.cg.mediaplayer.videos.exception.RecordNotFoundException;

@Service
public class TagsServiceImpl implements ITagsService{
	
	@Autowired
	private TagsDao tagsDao;
	
	@Override
	public List<Tags> getAllTags() {
		return tagsDao.findAll();
	}

	@Override
	public List<Tags> addTag(Tags bean) {
		Tags obj = tagsDao.save(bean);
		if(obj !=null) {
			return getAllTags();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Tags> update(Tags bean) throws RecordNotFoundException  {
		Tags p=tagsDao.save(bean);
		if(p!=null) {
			return getAllTags();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Tags> deleteById(Integer id) throws RecordNotFoundException  {
		tagsDao.deleteById(id);		
		return getAllTags();
	}

//	@Override
//	public List<Login> deleteByUserName(String userName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Tags findTagById(Integer id) throws RecordNotFoundException {
		Optional<Tags> op = tagsDao.findById(id);
		if(op.isPresent()) {
			//logger.info("Login is retrieved from database for LoginID "+id);
			return op.get();
		}
		else
		{
			//logger.error("Login is NOT retrieved from database for LoginID "+id);
			throw new RecordNotFoundException("Login with "+id + " not found");
		}		
	}

}

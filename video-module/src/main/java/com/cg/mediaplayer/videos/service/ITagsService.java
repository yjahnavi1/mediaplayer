package com.cg.mediaplayer.videos.service;

import java.util.List;

import com.cg.mediaplayer.videos.entity.Tags;
import com.cg.mediaplayer.videos.exception.RecordNotFoundException;

public interface ITagsService {

	List<Tags> getAllTags();

	List<Tags> addTag(Tags bean);

	List<Tags> update(Tags bean) throws RecordNotFoundException;

	List<Tags> deleteById(Integer id) throws RecordNotFoundException;

	Tags findTagById(Integer id) throws RecordNotFoundException;

}

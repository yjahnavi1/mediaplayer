package com.cg.mediaplayer.videos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mediaplayer.videos.entity.Tags;

public interface TagsDao  extends JpaRepository<Tags,Integer>{

}

package com.cg.mediaplayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.entity.Videos;


@Repository
public interface VideosDao extends JpaRepository<Videos,Integer>{
	public Videos findByTitle(String title);

}

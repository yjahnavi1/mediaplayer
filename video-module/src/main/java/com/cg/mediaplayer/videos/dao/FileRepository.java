package com.cg.mediaplayer.videos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.videos.entity.FileEntity;


@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {
}

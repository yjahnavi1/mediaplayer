package com.cg.mediaplayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.entity.Admin;


@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{
	public Admin findByUserName(String userName);
}

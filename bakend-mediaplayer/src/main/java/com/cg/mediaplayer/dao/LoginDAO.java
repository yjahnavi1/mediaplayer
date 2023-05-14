package com.cg.mediaplayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.entity.Login;

@Repository
public interface LoginDAO extends JpaRepository<Login,Integer>{

}

package com.cg.mediaplayer.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.login.entity.Login;

@Repository
public interface LoginDAO extends JpaRepository<Login,Integer>{

}

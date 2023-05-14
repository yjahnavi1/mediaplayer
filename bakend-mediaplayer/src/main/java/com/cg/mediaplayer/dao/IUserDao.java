package com.cg.mediaplayer.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.entity.User;


@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
	public User findByUserName(String userName);
}

package com.cg.mediaplayer.user.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.user.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
	public User findByUserName(String userName);
}

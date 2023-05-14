package com.cg.mediaplayer.login.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.mediaplayer.login.entity.Login;


@Repository
public interface LoginDAO extends JpaRepository<Login,Integer>{
	
//	@Modifying
//    @Query(value = "insert into login(login_id,password,role,user_name) values(:login_id,:password,:role, :user_name)", nativeQuery = true)
//    @Transactional
//    public void updateLoginRecord(@Param("login_id") Integer login_id,@Param("password") String password, @Param("role") String role, @Param("user_name") String user_name);

//	public Login addLoginRecord(Login bean);
	
//	@PersistenceContext
//    private EntityManager entityManager;
	
//	@Transactional
//	public Login addLoginRecord(Login bean) {
//	    entityManager.createNativeQuery("INSERT INTO login(password,role,user_name) VALUES (?,?,?)")
//	      .setParameter(1, bean.getPassword())
//	      .setParameter(2, bean.getRole())
//	      .setParameter(3, bean.getUserName())
//	      .executeUpdate();
//	}

}

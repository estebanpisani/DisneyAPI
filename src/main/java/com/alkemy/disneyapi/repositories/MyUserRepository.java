package com.alkemy.disneyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disneyapi.entities.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
	public MyUser findByUsername(String userName);
}

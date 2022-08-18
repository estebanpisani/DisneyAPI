package com.alkemy.disneyapi.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Data
public class MyUser{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private String username;
	private String password;
	

	@Override
	public String toString() {
		return "MyUser [id=" + id + ", username=" + username + ", password=" + password	+ "]";
	}

	
	
}

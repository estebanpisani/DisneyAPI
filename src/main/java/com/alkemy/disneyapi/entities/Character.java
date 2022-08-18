package com.alkemy.disneyapi.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "characters")
public class Character {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column(name="character_name")
	private String name;
	private String image;
	private Integer age;
	private Double weight;
	private String story;
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
	})
	@JoinTable(
			name = "movies_characters",
			joinColumns = @JoinColumn(name="character_id"),
			inverseJoinColumns = @JoinColumn(name="movie_id")
	)
	private List<Movie> movies = new ArrayList<>();
}

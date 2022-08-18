package com.alkemy.disneyapi.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String title;
	private String image;
	private LocalDate creationDate;
	private Integer rate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="genre_id")
	private Genre genre;
	@Column(name="genre_id", nullable = false)
	private String genreId;
	@ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
	private List<Character> characters = new ArrayList<>();

}

package com.TrackThat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//This is the class used to define the a user record

//Entity will specify that this is a entity and is mapped to a database table
@Entity
@Table(name="user_records")
public class UserRecord {
	
	//ID annotation defines the primary key 
	@Id
	//The Column annotation is used to define the columns in the database
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	//These are the fields of the class
	private int id;
	
	@Column(name="artist")
	private String artist;
	
	@Column(name="album_title")
	private String album_title;
	
	@Column(name="conditions")
	private String conditions;
	
	@Column(name="url")
	private String url;
	
	//Used to specify the relationship between user_records and user
	@ManyToOne
	//This is the join column in the DB. The user id is the foreign key in the user_records table. Defined as users_id.
	@JoinColumn(name="users_id")
	private User user;
	
	//empty constructor
	public UserRecord() {
		
	}
	
	//getters and setters for all fields
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum_title() {
		return album_title;
	}

	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}


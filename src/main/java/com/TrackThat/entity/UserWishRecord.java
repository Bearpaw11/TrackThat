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


//This is the class is used as the blueprint for the UserWishRecords
@Entity
//Table annotation specifies the user_wish_records to be used for mapping
@Table(name="user_wish_records")
public class UserWishRecord {
	
	//ID annotation defines the primary key 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//ID annotation defines the primary key 
	@Column(name="id")
	//These are the fields of the class
	private int id;
	
	@Column(name="artist")
	private String artist;
	
	@Column(name="album_title")
	private String album_title;
	
	@Column(name="url")
	private String url;
	
	//Used to specify the relationship between user_wish_records and user
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;
	
	
	//empty constructor
	public UserWishRecord() {
		
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}


package com.TrackThat.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//This is the class used to define the User

// Entity will specify that this is a entity and is mapped to a database table
@Entity
//Table annotation specifies the name of the database table to be used for mapping
@Table(name="users")
public class User {

	//ID annotation defines the primary key 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//The Column annotation is used to define the columns in the database
	@Column(name="id")
	//These are the fields of the class
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="user_name")
	private String userName;
	
	//This defines the relationship between the User and the UserRecord. mappedBy tells hibernate which variable we are using to represent the parent class in our child class.
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user")
	private List<UserRecord> userRecords = new ArrayList<>();

	//This defines the relationship between the User and the UserWishRecord class.
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user")
	private List<UserWishRecord> userWishRecords = new ArrayList<>();




	//empty constructor will be used to create a new instance
	public User() {
		
	}

	
	//getters and setters for all fields
	public List<UserWishRecord> getUserWishRecords() {
		return userWishRecords;
	}
	
	
	public void setUserWishRecords(List<UserWishRecord> userWishRecords) {
		this.userWishRecords = userWishRecords;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<UserRecord> getUserRecords() {
		return userRecords;
	}

	public void setUserRecords(List<UserRecord> userRecords) {
		this.userRecords = userRecords;
	}
	

}

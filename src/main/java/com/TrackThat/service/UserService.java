package com.TrackThat.service;

import java.util.List;

import com.TrackThat.entity.User;
import com.TrackThat.entity.UserRecord;
import com.TrackThat.entity.UserWishRecord;

public interface UserService {
    void signupUser(User user);
    boolean loginUser(User user);
    public UserRecord getUserRecord(int theId);
	public List<UserWishRecord> getUserWishRecords(int UserId);
	public void saveUserRecord(UserRecord theUserRecord);
	public void saveUserWishRecord(UserWishRecord theUserWishRecord);
//	public void saveUser(User theUser);
	public List<UserRecord> getUserRecords(int UserId);
	public void deleteUserRecord(int theId);
	public User verifyLogin(String userName);
	public void saveUserRecord(UserRecord theUserRecord, int i);
	public void saveUserWishRecord(UserWishRecord theUserWishRecord, int userId);
	public UserWishRecord getUserWishRecord(int theId);
	public void deleteUserWishRecord(int theId);
}

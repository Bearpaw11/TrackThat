package com.TrackThat.service;

import java.util.List;

import com.TrackThat.entity.User;
import com.TrackThat.entity.UserRecord;
import com.TrackThat.entity.UserWishRecord;

public interface UserService {
    void signupUser(User user);
    boolean loginUser(User user);
    public User verifyLogin(String userName);
}

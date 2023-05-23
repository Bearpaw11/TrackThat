package com.TrackThat.dao;

import org.springframework.data.repository.CrudRepository;

import com.TrackThat.entity.User;
import com.TrackThat.entity.UserRecord;

public interface UserRecordRepository extends CrudRepository<UserRecord, Integer> {

}

package com.TrackThat.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrackThat.dao.UserRepository;
import com.TrackThat.entity.User;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void signupUser(User user) {
        userRepository.save(user);
    }
    @Autowired
	private SessionFactory sessionFactory;


    @Override
    public boolean loginUser(User user) {
        User existingUser = userRepository.findByUserName(user.getUserName());
        return existingUser != null && existingUser.getPassword().equals(user.getPassword());
    }
    
    @Override
    public User verifyLogin(String theUserName) {
        Session currentSession = sessionFactory.openSession(); // Open a new session
        Query<User> theQuery = currentSession.createQuery("from User where userName=:theUserName", User.class);
        theQuery.setParameter("theUserName", theUserName);
        User user = theQuery.uniqueResult();
        
        // Close the session
        currentSession.close();

        if (user != null) {
            return user;
        }
        return null;
    }
}
//@Service
//public class UserServiceImpl implements UserService{
//	@Autowired
//	private UserDAO userDAO;
//	@Autowired
//	private UserRecordDAO userRecordDAO;
//	@Autowired
//	private UserWishRecordDAO userWishRecordDAO;
//	
//	
//	
//	@Override
//	@Transactional
//	public UserRecord getUserRecord(int theId) {
//		return userRecordDAO.getUserRecord(theId);
//	}
//	
//	
//	@Override
//	@Transactional
//	public void saveUserRecord(UserRecord theUserRecord) {
//		userRecordDAO.saveUserRecord(theUserRecord);
//	}
//	
//	
//	
//	@Override
//	@Transactional
//	public void saveUser(User theUser) {
//		userDAO.saveUser(theUser);
//	}
//
//
//	@Override
//	@Transactional
//	public List<UserRecord> getUserRecords(int UserId) {
//		return userRecordDAO.getUserRecords(UserId);
//	}
//
//	@Override
//	@Transactional
//	public void deleteUserRecord(int theId) {
//		userRecordDAO.deleteUserRecord(theId);
//		
//	}
//
//	@Override
//	@Transactional
//	public User verifyLogin(String theUserName) {
//		return userDAO.verifyLogin(theUserName);		
//	}
//
//
//	@Override
//	@Transactional
//	public void saveUserRecord(UserRecord theUserRecord, int UserId) {
//		userDAO.saveUserRecord(theUserRecord, UserId);
//		
//	}
//
//	@Override
//	@Transactional
//	public void saveUserWishRecord(UserWishRecord theUserWishRecord, int userId) {
//		userDAO.saveUserWishRecord(theUserWishRecord, userId);
//		
//	}
//	@Override
//	@Transactional
//	public List<UserWishRecord> getUserWishRecords(int UserId) {
//		return userWishRecordDAO.getUserWishRecords(UserId);
//	}
//
//	@Override
//	@Transactional
//	public UserWishRecord getUserWishRecord(int theId) {
//		return userWishRecordDAO.getUserWishRecord(theId);
//	}
//
//
//	@Override
//	@Transactional
//	public void deleteUserWishRecord(int theId) {
//		userWishRecordDAO.deleteUserWishRecord(theId);
//		
//	}
//}


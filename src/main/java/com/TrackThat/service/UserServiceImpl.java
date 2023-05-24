package com.TrackThat.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.TrackThat.dao.UserRepository;
import com.TrackThat.entity.User;
import com.TrackThat.entity.UserRecord;
import com.TrackThat.entity.UserWishRecord;



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
    	 Session currentSession = sessionFactory.openSession();
		    Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

//		        Session currentSession = sessionFactory.openSession(); // Open a new session
		        Query<User> theQuery = currentSession.createQuery("from User where userName=:theUserName", User.class);
		        theQuery.setParameter("theUserName", theUserName);
		        User user = theQuery.uniqueResult();
		        
		        transaction.commit();
		        if (user != null) {
		            return user;
		        }
		        return null;

		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
    	
    	
    	
    	
//        Session currentSession = sessionFactory.openSession(); // Open a new session
//        Query<User> theQuery = currentSession.createQuery("from User where userName=:theUserName", User.class);
//        theQuery.setParameter("theUserName", theUserName);
//        User user = theQuery.uniqueResult();
//        
//        // Close the session
//        currentSession.close();
//
//        if (user != null) {
//            return user;
//        }
//        return null;
    }
    
    
	@Override
	public void saveUserWishRecord(UserWishRecord theUserWishRecord) {
		 Session currentSession = sessionFactory.openSession();
		    Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		        currentSession.saveOrUpdate(theUserWishRecord);
		        
		        transaction.commit();

		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
	}
    
	
	//Method to save a userWishRecord
	@Override
	public void saveUserWishRecord(UserWishRecord theUserWishRecord, int userId) {
		 Session currentSession = sessionFactory.openSession();
		    Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		        User user = currentSession.get(User.class, userId);
				theUserWishRecord.setUser(user);
				currentSession.saveOrUpdate(theUserWishRecord);
				transaction.commit();

		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
		
	}
	
	@Override
	public UserRecord getUserRecord(int theId){
		
		//creating a session with the database
		Session currentSession = sessionFactory.openSession();
		
		  Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		        UserRecord userRecord = currentSession.get(UserRecord.class, theId);
				transaction.commit();
				return userRecord;

		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
	}
	
	//method to save or update a record
	@Override
	public void saveUserRecord(UserRecord theUserRecord) {
		Session currentSession = sessionFactory.openSession();
		 Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		        currentSession.saveOrUpdate(theUserRecord);
				transaction.commit();

		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
		
	}
	
	@Override
	public void saveUserRecord(UserRecord theUserRecord, int UserId) {
		 Session currentSession = sessionFactory.openSession();
		    Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		        User user = currentSession.get(User.class, UserId);
				System.out.println(user.getUserName());
				theUserRecord.setUser(user);
				currentSession.saveOrUpdate(theUserRecord);

		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
	}
	

	//method to get and return a list of userRecords
	@Override
	public List<UserRecord> getUserRecords(int UserId) {
		Session currentSession = sessionFactory.openSession();
		Transaction transaction = null;

	    try {
	        transaction = currentSession.beginTransaction();

	        Query<UserRecord> theQuery = currentSession.createQuery("from UserRecord where users_id=:UserId order by artist", UserRecord.class);
			//setting the Parameter to the UserId
			theQuery.setParameter("UserId", UserId);
			//Creating a list of records based on theQuery results
			List<UserRecord> theUserRecords = theQuery.getResultList();
			//Return the User records
			transaction.commit();
			return theUserRecords;

	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw e;
	    } finally {
	        currentSession.close();
	    }
	}
	//Method to delete a userRecords
//	@Override
//	public void deleteUserRecord(int theId) {
//		Session currentSession = sessionFactory.openSession();
//		
//		UserRecord theUserRecord = currentSession.get(UserRecord.class, theId);
//		System.out.println("1");
//		currentSession.delete(theUserRecord);
//		System.out.println(theUserRecord);
//			
//	}@Override
	public void deleteUserRecord(int theId) {
	    Session currentSession = sessionFactory.openSession();
	    Transaction transaction = null;

	    try {
	        transaction = currentSession.beginTransaction();

	        UserRecord theUserRecord = currentSession.get(UserRecord.class, theId);
	        currentSession.delete(theUserRecord);

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw e;
	    } finally {
	        currentSession.close();
	    }
	}
	@Override
	public List<UserWishRecord> getUserWishRecords(int UserId){
		
		// get the current session 
		Session currentSession = sessionFactory.openSession();
		   Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		        Query<UserWishRecord> theQuery = currentSession.createQuery("from UserWishRecord where users_id=:UserId order by artist", UserWishRecord.class);
				//setting the Parameter to the UserId
				theQuery.setParameter("UserId", UserId);
				List<UserWishRecord> theUserWishRecords = theQuery.getResultList();
				transaction.commit();
				return theUserWishRecords;

		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
		
		//Parameterized Query to get userWishRecords by UserId
		
	}
	
	//method to save or update a record


	//getting the userWishRecord with the Id and setting it to userWishRecord and then returning the userWishRecord
	@Override
	public UserWishRecord getUserWishRecord(int theId) {
		Session currentSession = sessionFactory.openSession();
		 Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		    	UserWishRecord userWishRecord = currentSession.get(UserWishRecord.class, theId);

		    	transaction.commit();
				return userWishRecord;//

		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
		
	 
		
	}

	//deleting a UserWishRecord
	@Override
	public void deleteUserWishRecord(int theId) {
		 Session currentSession = sessionFactory.openSession();
		    Transaction transaction = null;

		    try {
		        transaction = currentSession.beginTransaction();

		        UserWishRecord theUserWishRecord = currentSession.get(UserWishRecord.class, theId);
		        currentSession.delete(theUserWishRecord);

		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e;
		    } finally {
		        currentSession.close();
		    }
	}

//	@Override
//	public void saveUser(User theUser) {
//		// TODO Auto-generated method stub
//		
//	}
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


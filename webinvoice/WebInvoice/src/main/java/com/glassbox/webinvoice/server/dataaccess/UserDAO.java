package com.glassbox.webinvoice.server.dataaccess;

import com.glassbox.webinvoice.shared.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<User,Long> {
    
    	public UserDAO() {
		super(User.class);
	}
        
	@SuppressWarnings("unchecked")
	public User findUser(String username) {
		User user = null;

		try {
			user = (User) sf.getCurrentSession()
					.createQuery("from User u where u.username=:username")
					.setString("username", username).uniqueResult();
		} catch (Exception e) {

		}

		return user;
	}

        @SuppressWarnings("unchecked")
	public User authenticateUser(String login, String password) {
		User user = null;

		try {
                        //authenticate with username, password
			user = (User) sf.getCurrentSession()
					.createQuery("from User u where u.username=:username and u.password=:password")
					.setString("username", login).setString("password", password).uniqueResult();
                        
                        //check if authentication successful
                        if (user != null) {
                            return user;
                        }

                        //authenticate with email, password
                        user = (User) sf.getCurrentSession()
                                        .createQuery("from User u inner join Email e where e.userID = u.id and e.emailAddress=:username and " +
                                                     "u.primaryEmailId=e.id and u.password=:password")
                                        .setString("username", login).setString("password", password).uniqueResult();
                        

                        //check if authentication successful
                        if (user != null) {
                            return user;
                        }
		} catch (Exception e) {
                    //exception logging.
		}

		return user;
	}
        
}

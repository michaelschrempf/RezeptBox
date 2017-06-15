package at.fh.swenga.account.service;

import at.fh.swenga.model.UserModel;

public interface UserService {
	    void save(UserModel user);

	    UserModel findByUsername(String username);
	}
	


package at.fh.swenga.account.service;

import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.UserModel;

@Transactional
public interface UserService {
		@Transactional
	    public void save(UserModel user);
	    @Transactional
	    public UserModel findByUsername(String username);
	}
	


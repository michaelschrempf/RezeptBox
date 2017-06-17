package at.fh.swenga.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.UserModel;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	@Transactional
    public UserModel findByUsername(String username);
	
	@Transactional
    public UserModel findByLastName(String lastName);
	
	@Transactional
    public UserModel findByFirstName(String firstName);
	
	@Transactional
    public UserModel findByIdUser(Integer id);
	
	
}
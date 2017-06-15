package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	@Transactional
    UserModel findByUsername(String username);
}
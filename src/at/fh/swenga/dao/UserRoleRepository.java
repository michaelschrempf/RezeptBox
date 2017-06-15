package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.UserRoleModel;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleModel, Integer> {

}

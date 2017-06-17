package at.fh.swenga.dao;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.UserRoleModel;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public interface UserRoleRepository extends JpaRepository<UserRoleModel, Integer> {

	@Transactional
	public List<UserRoleModel> findByUserRoleId(Integer roleId);
	@Transactional
	public List<UserRoleModel> findByRole(String role);
	
}

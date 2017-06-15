package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserRoleModel")
public class UserRoleModel implements java.io.Serializable{
private static final long serialVersionUID = 8098173157518993615L;

	@Id
	@Column(name = "userRoleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userRoleId;
	@Column(name = "users")
	private Set<UserModel> users;
	@Column(name = "role", nullable = false, length = 45)
	private String role;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id", unique = true, nullable = false)
	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	 @ManyToMany(mappedBy = "roles")
	  public Set<UserModel> getUsers() {
	        return users;
	 }

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

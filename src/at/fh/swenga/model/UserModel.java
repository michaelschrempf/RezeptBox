package at.fh.swenga.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;




@Entity
@Table(name = "UserModel")
public class UserModel implements java.io.Serializable {

	// Attribute
	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 45)
	private String firstName;

	@Column(nullable = false, length = 45)
	private String lastName;

	@Column(nullable = false, length = 45)
	private String username;

	@Column(nullable = false, length = 45)
	private String password;
	
	@Column(nullable = false, length = 45)
	private String passwordConfirm;
	
	@Column
	@OneToMany(mappedBy = "userModel", fetch = FetchType.EAGER)
	private Set<RecipeModel> recipes;
	
	@Column
	@ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UserRoleModel> roles;

	

	/*
	 * @OneToMany(mappedBy="userModel", fetch=FetchType.EAGER) private
	 * Set<LikeModel> likes;
	 * 
	 * @OneToMany(mappedBy="userModel", fetch=FetchType.EAGER) private
	 * Set<CommentModel> comments;
	 */
	public UserModel(int id, String firstName, String lastName, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<RecipeModel> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RecipeModel> recipes) {
		this.recipes = recipes;
	}

    public Set<UserRoleModel> getRoles() {
        return roles;
    }

	public void setRoles(Set<UserRoleModel> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	/*
	 * public Set<LikeModel> getLikes() { return likes; }
	 * 
	 * public void setLikes(Set<LikeModel> likes) { this.likes = likes; }
	 * 
	 * public Set<CommentModel> getComments() { return comments; }
	 * 
	 * public void setComments(Set<CommentModel> comments) { this.comments =
	 * comments; }
	 */
}

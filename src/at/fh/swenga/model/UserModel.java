package at.fh.swenga.model;

import java.util.HashSet;
import java.util.List;
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
	@Column(name = "idUser")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;

	@Column(nullable = true, length = 45)
	private String firstName;

	@Column(nullable = true, length = 45)
	private String lastName;

	@Column(nullable = false, length = 45)
	private String username;

	@Column(nullable = false, length = 200)
	private String password;
	
	@Column(nullable = true, length = 200)
	private String passwordConfirm;
	
	@Column
	@OneToMany(mappedBy = "userModel", fetch = FetchType.EAGER)
	private List<RecipeModel> recipes;
	
	@Column
	@ManyToMany
    @JoinTable
	private List<UserRoleModel> roles;

	

	/*
	 * @OneToMany(mappedBy="userModel", fetch=FetchType.EAGER) private
	 * Set<LikeModel> likes;
	 * 
	 * @OneToMany(mappedBy="userModel", fetch=FetchType.EAGER) private
	 * Set<CommentModel> comments;
	 */
	public UserModel(int idUser, String firstName, String lastName, String username, String password, List<UserRoleModel> roles, List<RecipeModel> recipes) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.recipes = recipes;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<RecipeModel> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeModel> recipes) {
		this.recipes = recipes;
	}

    public List<UserRoleModel> getRoles() {
        return roles;
    }

	public void setRoles(List<UserRoleModel> list) {
		this.roles = list;
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

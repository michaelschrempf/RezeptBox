package at.fh.swenga.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



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
	private String userName;
	
	@Column(nullable = false, length = 45)
	private String password;

	
	@OneToMany(mappedBy="userModel", fetch=FetchType.EAGER)
	private Set<RecipeModel> recipes;
	
	@OneToMany(mappedBy="userModel", fetch=FetchType.EAGER)
	private Set<LikeModel> likes;
	
	@OneToMany(mappedBy="userModel", fetch=FetchType.EAGER)
	private Set<CommentModel> comments;
	
	public UserModel(int id, String firstName, String lastName, String userName, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<LikeModel> getLikes() {
		return likes;
	}

	public void setLikes(Set<LikeModel> likes) {
		this.likes = likes;
	}

	public Set<CommentModel> getComments() {
		return comments;
	}

	public void setComments(Set<CommentModel> comments) {
		this.comments = comments;
	}

}

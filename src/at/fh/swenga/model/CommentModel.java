package at.fh.swenga.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "CommentModel")
public class CommentModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_comment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="userModel_fk")
	UserModel userModel;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="recipeModel_fk")
	RecipeModel recipeModel;
	
	@Column(nullable = false, length = 1000)
	private String text;

	@Version
	long version;
	/*-------------------------------------------------*/


	/*-------------------------------------------------*/
	public CommentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentModel(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserModel getUsermodel() {
		return userModel;
	}

	public void setUsermodel(UserModel userModel) {
		this.userModel = userModel;
	}

	public RecipeModel getRecipeModel() {
		return recipeModel;
	}

	public void setRecipeModel(RecipeModel recipeModel) {
		this.recipeModel = recipeModel;
	}

}

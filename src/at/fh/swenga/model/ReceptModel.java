package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Rezepte")
public class ReceptModel implements java.io.Serializable {

	// Attribute
	@Id
	@Column(name = "id_recept")
	private int id;

	@Column(nullable = false, length = 45)
	private String name;

	@Column(nullable = false, length = 500)
	private String beschreibung;

	@Column(nullable = false, length = 1000)
	private String zubereitung;

	@Version
	long version;

	/*Realtionships*/
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="userModel_fk")
	UserModel usermodel;
	
	@OneToMany(mappedBy="receptmodel", fetch=FetchType.EAGER)
	private Set<LikeModel> likes;
	
	@OneToMany(mappedBy="receptingredientmodel", fetch=FetchType.EAGER)
	private Set<ReceptIngredientModel> receptingredients;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="receptCategorieModel_fk")
	ReceptCategorieModel receptCategorieModel;
	
	/*-----------------------*/
	public ReceptModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReceptModel(int id,String name, String beschreibung, String zubereitung) {
		super();
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
		this.zubereitung = zubereitung;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getZubereitung() {
		return zubereitung;
	}

	public void setZubereitung(String zubereitung) {
		this.zubereitung = zubereitung;
	}
	public UserModel getUsermodel() {
		return usermodel;
	}

	public void setUsermodel(UserModel usermodel) {
		this.usermodel = usermodel;
	}

	public Set<LikeModel> getLikes() {
		return likes;
	}

	public void setLikes(Set<LikeModel> likes) {
		this.likes = likes;
	}

	public ReceptCategorieModel getReceptCategorieModel() {
		return receptCategorieModel;
	}

	public void setReceptCategorieModel(ReceptCategorieModel receptCategorieModel) {
		this.receptCategorieModel = receptCategorieModel;
	}

	public Set<ReceptIngredientModel> getReceptingredients() {
		return receptingredients;
	}

	public void setReceptingredients(Set<ReceptIngredientModel> receptingredients) {
		this.receptingredients = receptingredients;
	}
}

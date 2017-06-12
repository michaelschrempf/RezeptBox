package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
//@Table(name = "Rezepte")
public class ReceptModel implements java.io.Serializable {

	// Attribute
	@Id
	@Column(name = "id_recept")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	
	/*-----------------------*/
	public ReceptModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReceptModel(String name, String beschreibung, String zubereitung) {
		super();
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

	public int getId1() {
		return id;
	}

	public void setId1(int id) {
		this.id = id;
	}

	public UserModel getUsermodel() {
		return usermodel;
	}

	public void setUsermodel(UserModel usermodel) {
		this.usermodel = usermodel;
	}
}

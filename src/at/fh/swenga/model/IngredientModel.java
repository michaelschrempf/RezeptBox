package at.fh.swenga.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
public class IngredientModel implements java.io.Serializable {

	@Id
	@Column(name = "id_ingredient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(nullable = false)
	private int menge;

	/*---------------------------------------------------------*/
	

	/*---------------------------------------------------------*/

	public IngredientModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngredientModel(String name, int menge) {
		super();
		this.name = name;
		this.menge = menge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

}

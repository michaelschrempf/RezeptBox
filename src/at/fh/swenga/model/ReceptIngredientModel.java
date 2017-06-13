package at.fh.swenga.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RezeptZutat")
public class ReceptIngredientModel implements java.io.Serializable {
	private static final long serialVersionUID = 8098173157518993615L;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="ingredientModel_fk")
	IngredientModel ingredientModel;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="receptModel_fk")
	ReceptModel receptModel;
		
	@Id
	@Column(name = "id_recept")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public ReceptIngredientModel(int id) {
		super();
		this.id = id;
	}
	public ReceptIngredientModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}

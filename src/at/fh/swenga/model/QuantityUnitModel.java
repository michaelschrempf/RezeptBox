package at.fh.swenga.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QuantityUnitModel")
public class QuantityUnitModel implements java.io.Serializable {
	
	@Id
	@Column(name = "id_ingredient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 45)
	private String name;
	
	//Relationship
	
	/*-----------------------------------------------*/
	
	public QuantityUnitModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuantityUnitModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

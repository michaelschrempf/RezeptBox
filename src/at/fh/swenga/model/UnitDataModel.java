package at.fh.swenga.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class UnitDataModel implements java.io.Serializable {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUnitData;

	
	@Column
	private String name;
	

	@Column
	@OneToMany(mappedBy = "unitDataModel", fetch = FetchType.EAGER)
	private Set<IngredientModel> ingredientModels;
	
	
	
	public UnitDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public UnitDataModel(int idUnitData, String name, Set<IngredientModel> ingredientModels) {
		super();
		this.idUnitData = idUnitData;
		this.name = name;
		this.ingredientModels = ingredientModels;
	}





	public int getIdUnitData() {
		return idUnitData;
	}






	public void setIdUnitData(int idUnitData) {
		this.idUnitData = idUnitData;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public Set<IngredientModel> getIngredientModels() {
		return ingredientModels;
	}








	public void setIngredientModels(Set<IngredientModel> ingredientModels) {
		this.ingredientModels = ingredientModels;
	}








	public void add(IngredientModel ingredient) {
		if (ingredientModels == null) {
			ingredientModels= new HashSet<IngredientModel>();
		}
		
		
		ingredientModels.add(ingredient);
	}

	
	
	

	


	



	

}

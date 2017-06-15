package at.fh.swenga.model;

import java.util.List;
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
@Table(name = "RecipeCategoryModel")
public class RecipeCategoryModel implements java.io.Serializable {

	/*@OneToMany(mappedBy="receptmodel", fetch=FetchType.EAGER)
	private Set<ReceptModel> receptModel;*/
	
	@Id
	@Column(name = "id_recipeCategory")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 45)
	private String name;
	

	public RecipeCategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeCategoryModel(String name) {
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

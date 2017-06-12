package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserModel implements java.io.Serializable {
	// Attribute
	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 45)
	private String name;
	
	@OneToMany(mappedBy="usermodel", fetch=FetchType.EAGER)
	private Set<ReceptModel> recepts;
	
	public UserModel(String name) {
		super();
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Set<ReceptModel> getRecepts() {
		return recepts;
	}
	public void setRecepts(Set<ReceptModel> recepts) {
		this.recepts = recepts;
	}

}

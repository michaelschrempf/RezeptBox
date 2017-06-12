package at.fh.swenga.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Table(name = "like")
public class LikeModel implements java.io.Serializable{
	
	/*@JoinColumn(name="id_user", nullable = false)
	@ManyToOne
	private UserModel user;
	
	@JoinColumn(name="id_recept", nullable = false)
	@ManyToOne
	private ReceptModel recept;*/

	@Version
	long version;
	
	@Id
	@Column(name = "id_like")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

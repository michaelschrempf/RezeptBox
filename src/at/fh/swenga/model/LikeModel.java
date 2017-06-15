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
@Table(name = "LikeModel")
public class LikeModel implements java.io.Serializable{
/*	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="userModel_fk")
	UserModel userModel;
*/	
	/*@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="receptModel_fk")
	ReceptModel receptModel;*/

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
/*
	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
*/
	/*public ReceptModel getReceptModel() {
		return receptModel;
	}

	public void setReceptModel(ReceptModel receptModel) {
		this.receptModel = receptModel;
	}*/
}

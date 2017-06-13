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
@Table(name = "Kommentare")
public class CommentModel implements java.io.Serializable {

	@Id
	@Column(name = "id_comment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="userModel_fk")
	UserModel usermodel;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="receptModel_fk")
	ReceptModel receptModel;
	
	@Column(nullable = false, length = 1000)
	private String text;

	@Version
	long version;
	/*-------------------------------------------------*/


	/*-------------------------------------------------*/
	public CommentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentModel(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserModel getUsermodel() {
		return usermodel;
	}

	public void setUsermodel(UserModel usermodel) {
		this.usermodel = usermodel;
	}

	public ReceptModel getReceptModel() {
		return receptModel;
	}

	public void setReceptModel(ReceptModel receptModel) {
		this.receptModel = receptModel;
	}

}

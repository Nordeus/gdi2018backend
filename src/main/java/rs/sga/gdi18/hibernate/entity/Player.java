package rs.sga.gdi18.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerIdSeq")
	@SequenceGenerator(name = "playerIdSeq", allocationSize = 1, sequenceName = "player_id_seq")
	private Integer id;

	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Player [id=%s, username=%s]", id, username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

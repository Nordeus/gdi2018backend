package rs.sga.gdi18.hibernate.entity;

public class Player {

	private Integer id;

	private String username;

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return String.format("Player [id=%s, username=%s]", id, username);
	}
}

package rs.sga.gdi18.hibernate.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Battle {

	public static final int VICTORY_XP = 50;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "battleIdSeq")
	@SequenceGenerator(name = "battleIdSeq", allocationSize = 1, sequenceName = "battle_id_seq")
	private Integer id;

	private int playerId;

	private Instant endedAt;

	private boolean won;

	private int score;

	public Instant getEndedAt() {
		return endedAt;
	}

	public Integer getId() {
		return id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getScore() {
		return score;
	}

	public boolean isWon() {
		return won;
	}

	public void setEndedAt(final Instant endedAt) {
		this.endedAt = endedAt;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	public void setWon(final boolean won) {
		this.won = won;
	}

	@Override
	public String toString() {
		return String.format("Battle [id=%s, playerId=%s, endedAt=%s, won=%s, score=%s]", id, playerId, endedAt, won,
				score);
	}
}
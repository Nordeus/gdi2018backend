package rs.sga.gdi18.serializer;

import org.json.JSONObject;

import rs.sga.gdi18.hibernate.entity.Battle;

public class BattleSerializer {

	public JSONObject toJson(Battle battle) {
		return new JSONObject()
				.put("id", battle.getId())
				.put("endedAt", battle.getEndedAt().toEpochMilli())
				.put("won", battle.isWon())
				.put("score", battle.getScore());
	}
}

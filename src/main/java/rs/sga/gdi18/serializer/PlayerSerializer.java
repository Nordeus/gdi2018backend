package rs.sga.gdi18.serializer;

import org.json.JSONObject;

import rs.sga.gdi18.hibernate.entity.Player;

public class PlayerSerializer {

	public JSONObject toJson(Player player) {
		JSONObject playerJson = new JSONObject();

		playerJson.put("id", player.getId());
		playerJson.put("username", player.getUsername());

		return playerJson;
	}
}

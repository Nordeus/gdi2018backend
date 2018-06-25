package rs.sga.gdi18.servlet;

import static rs.sga.gdi18.hibernate.DataAccessLayer.transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.sga.gdi18.hibernate.entity.Player;
import rs.sga.gdi18.serializer.PlayerSerializer;

@Path("/player/{playerId}/battle")
public class BattleServlet {

	private static final Logger LOG = LoggerFactory.getLogger(BattleServlet.class);

	private static final int VICTORY_XP = 50;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createBattleForPlayer(@PathParam("playerId") Integer playerId, String body) {
		JSONObject request = new JSONObject(body);
		return transactional(session -> {
			Player player = session.get(Player.class, playerId);
			if (player == null) {
				LOG.error("Player(id={}) does not exist", playerId);
				return null;
			}
			boolean won = request.getBoolean("won");
			if (won) {
				player.setXp(player.getXp() + VICTORY_XP);
				session.update(player);
			}

			LOG.debug("{} finished battle.", player);
			return new PlayerSerializer().toJson(player).toString();
		});
	}

}

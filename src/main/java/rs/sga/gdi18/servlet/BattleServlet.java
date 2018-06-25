package rs.sga.gdi18.servlet;

import static rs.sga.gdi18.hibernate.DataAccessLayer.transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.sga.gdi18.hibernate.entity.Battle;
import rs.sga.gdi18.hibernate.entity.Player;
import rs.sga.gdi18.serializer.BattleSerializer;
import rs.sga.gdi18.serializer.PlayerSerializer;

@Path("/player/{playerId}/battle")
public class BattleServlet {

	private static final Logger LOG = LoggerFactory.getLogger(BattleServlet.class);
	
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
				player.setXp(player.getXp() + Battle.VICTORY_XP);
				session.update(player);
			}

			LOG.debug("{} finished battle.", player);
			Battle battle = new Battle();
			battle.setPlayerId(playerId);
			battle.setWon(won);
			battle.setScore(request.getInt("score"));
			battle.setEndedAt(Instant.now());
			session.save(battle);

			LOG.debug("{} finished.", battle);
			return new PlayerSerializer().toJson(player).toString();
		});
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getBattleHistory(@PathParam("playerId") Integer playerId) {
		Collection<Battle> battles = transactional(session -> {
			Player player = session.get(Player.class, playerId);
			if (player == null) {
				LOG.error("Player(id={}) does not exist", playerId);
				return null;
			}
			return player.getBattles();
		});
		if (battles == null) {
			return null;
		}

		LOG.debug("Found {} battles for Player [id={}]", battles.size(), playerId);
		Collection<JSONObject> serializedBattles = new ArrayList<>();
		for (Battle battle : battles) {
			serializedBattles.add(new BattleSerializer().toJson(battle));
		}

		return new JSONObject().put("battles", serializedBattles).toString();
	}

}

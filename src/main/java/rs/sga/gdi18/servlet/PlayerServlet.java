package rs.sga.gdi18.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.sga.gdi18.hibernate.DataAccessLayer;
import rs.sga.gdi18.hibernate.entity.Player;

@Path("/player")
public class PlayerServlet {
	private static final Logger LOG = LoggerFactory.getLogger(PlayerServlet.class);
	
	@GET
	public String getPlayer() {
		int playerId = 1;
		Player player = DataAccessLayer.transactional(session -> session.get(Player.class, playerId));
		if (player == null) {
			LOG.debug("Player with ID 1 wasn't found!");
			return null;
		}
		LOG.debug("Found {}", player);
		return player.toString();
	}
}

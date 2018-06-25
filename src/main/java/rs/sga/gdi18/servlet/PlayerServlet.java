package rs.sga.gdi18.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.sga.gdi18.hibernate.DataAccessLayer;
import rs.sga.gdi18.hibernate.entity.Player;
import rs.sga.gdi18.serializer.PlayerSerializer;

@Path("/player")
public class PlayerServlet {
	private static final Logger LOG = LoggerFactory.getLogger(PlayerServlet.class);
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String getPlayer() {
//		int playerId = 1;
//		Player player = DataAccessLayer.transactional(session -> session.get(Player.class, playerId));
//		if (player == null) {
//			LOG.debug("Player with ID 1 wasn't found!");
//			return null;
//		}
//		LOG.debug("Found {}", player);
//		return new PlayerSerializer().toJson(player).toString();
//	}
	
	@GET
	@SuppressWarnings("unchecked")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@QueryParam("username") String username) {
		Player player = DataAccessLayer.transactional(session -> {
			Query<Player> query = session.createQuery("from Player where username = :username");
			query.setParameter("username", username);
			return query.uniqueResult();
		});
		if (player == null) {
			return null;
		}
		LOG.debug("{} logged in", player);
		return new PlayerSerializer().toJson(player).toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String register(@QueryParam("username") String username) {
		Player player = DataAccessLayer.transactional(session -> {
			Player p = new Player();
			p.setUsername(username);
			session.save(p);
			return p;
		});
		LOG.debug("{} registered", player);
		return new PlayerSerializer().toJson(player).toString();
	}
}

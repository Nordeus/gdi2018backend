package rs.sga.gdi18.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rs.sga.gdi18.hibernate.DataAccessLayer;
import rs.sga.gdi18.hibernate.entity.Player;

@Path("/hello")
public class HelloServlet {

	@GET
	public String doGet() {
		Player p = DataAccessLayer.transactional(session -> {
			Player player = new Player();
			player.setUsername("tester");
			session.save(player);

			return player;
		});

		return "Created: " + p;
	}
}
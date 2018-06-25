package rs.sga.gdi18.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloServlet {

	@GET
	public String doGet() {
		return "Hello, world!";
	}
}
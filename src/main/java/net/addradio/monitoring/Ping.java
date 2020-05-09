package net.addradio.monitoring;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Ping
{
	@GET
	@Path("ping")
	@Produces({ MediaType.TEXT_PLAIN })
	public String ping()
	{
		System.out.println("ping");
		return "pong";
	}
}

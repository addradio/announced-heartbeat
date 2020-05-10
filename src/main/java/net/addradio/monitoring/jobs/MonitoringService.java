package net.addradio.monitoring.jobs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("jobs")
public class MonitoringService
{
	@GET
	@Path("")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getJobs()
	{
		System.out.println("getJobs");
		String jsonJobs = "{\"jobs\": []}";
		return Response.ok(jsonJobs).build();
	}
}

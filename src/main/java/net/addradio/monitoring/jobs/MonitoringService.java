package net.addradio.monitoring.jobs;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.addradio.monitoring.entity.Job;

@Path("jobs")
public class MonitoringService
{

	@Inject
	private Registry jobsRegistry;

	@GET
	@Path("")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllJobs()
	{
		System.out.println("getting all Jobs");
		List<Job> jobs = jobsRegistry.getAll();
		return Response.ok(jobs).build();
	}

	@POST
	@Path("{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response createJob(@PathParam(value = "name") String name)
	{
		System.out.println("create Job named " + name);

		long jobId = jobsRegistry.createJob(name).getJobId();

		return Response.ok(jobId).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteJob(@PathParam(value = "id") Long jobId)
	{
		System.out.println("delete Job with id " + jobId);

		boolean deleted = jobsRegistry.deleteJob(jobId);
		System.out.println(deleted ? " deleted " : " not deleted");

		return Response.ok().build();
	}

	@GET
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getJobByName(@PathParam(value = "name") String name)
	{
		System.out.println("getting Job " + name);
		Job job = jobsRegistry.get(name);
		return Response.ok(job.getJobId()).build();
	}

}

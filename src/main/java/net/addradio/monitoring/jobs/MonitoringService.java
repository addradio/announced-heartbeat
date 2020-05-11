package net.addradio.monitoring.jobs;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.addradio.monitoring.entity.EventType;
import net.addradio.monitoring.entity.Job;
import net.addradio.monitoring.entity.LifecycleEvent;

@Path("jobs")
public class MonitoringService
{

	@Inject
	private Registry jobsRegistry;

	@Inject
	private EventLogger eventLogger;

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

	// TODO schneider/fnowotny funktioniert noch nicht wenn Events des Jobs da sind
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

	@PUT
	@Path("{id}/event/starting")
	public Response starting(@PathParam(value = "id") Long jobId)
	{
		System.out.println("starting Job with id " + jobId);
		return putEvent(jobId, EventType.STARTING, null);
	}

	@PUT
	@Path("{id}/event/finished")
	public Response finished(@PathParam(value = "id") Long jobId)
	{
		System.out.println("finished Job with id " + jobId);
		return putEvent(jobId, EventType.FINISHED, null);
	}

	@PUT
	@Path("{id}/event/failed")
	public Response failed(@PathParam(value = "id") Long jobId, @QueryParam(value = "msg") String msg)
	{
		System.out.println("failed Job with id " + jobId + ", msg=" + msg);
		return putEvent(jobId, EventType.FAILED, msg);
	}

	private Response putEvent(Long jobId, EventType type, String message)
	{
		Job job = jobsRegistry.get(jobId);
		if (job == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		LifecycleEvent event = eventLogger.createLifecycleEvent(job, type, message);
		return Response.ok(event.getId()).build();
	}

}

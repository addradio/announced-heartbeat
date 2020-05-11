package net.addradio.monitoring.jobs;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.addradio.monitoring.entity.Job;

@Path("reports")
public class ReportingService
{

	@Inject
	private Registry jobsRegistry;

	@Inject
	private EventLogger eventLogger;

	@GET
	@Path("")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll()
	{
		System.out.println("getting all events of all jobs");
		List<JobReport> jobReports = new ArrayList<>();
		List<Job> jobs = jobsRegistry.getAll();
		for (Job job : jobs)
		{
			JobReport report = new JobReport(job, eventLogger.getEvents(job));
			jobReports.add(report);
		}
		return Response.ok(jobReports).build();
	}

}

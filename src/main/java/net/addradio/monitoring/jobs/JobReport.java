package net.addradio.monitoring.jobs;

import java.io.Serializable;
import java.util.List;

import net.addradio.monitoring.entity.Job;
import net.addradio.monitoring.entity.LifecycleEvent;

public class JobReport implements Serializable
{
	private static final long serialVersionUID = -3008167561766530017L;

	private Job job;
	private List<LifecycleEvent> events;

	public JobReport(Job job, List<LifecycleEvent> events)
	{
		this.setJob(job);
		this.setEvents(events);
	}

	public Job getJob()
	{
		return job;
	}

	public void setJob(Job job)
	{
		this.job = job;
	}

	public List<LifecycleEvent> getEvents()
	{
		return events;
	}

	public void setEvents(List<LifecycleEvent> events)
	{
		this.events = events;
	}

}

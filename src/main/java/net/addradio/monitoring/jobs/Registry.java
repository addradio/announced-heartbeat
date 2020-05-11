package net.addradio.monitoring.jobs;

import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.addradio.monitoring.entity.Job;

@Singleton
@LocalBean

public class Registry
{
	@PersistenceContext(unitName = "monitoring")
	private EntityManager em;

	public Job createJob(String name)
	{
		Job existingJob = get(name);
		if (existingJob != null)
		{
			return existingJob;
		}

		Job job = new Job(name);
		em.persist(job);
		return job;
	}

	public boolean deleteJob(Long jobId)
	{
		Job toDelete = get(jobId);
		if (toDelete != null)
		{
			em.remove(toDelete);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Job> getAll()
	{
		String criteriaQuery = "from Job job";
		Query query = em.createQuery(criteriaQuery);
		try
		{
			return (List<Job>) query.getResultList();
		}
		catch (Exception e)
		{
		}
		return Collections.emptyList();
	}

	public Job get(String name)
	{
		String criteriaQuery = "from Job job where job.name=:name";
		Query query = em.createQuery(criteriaQuery);
		query.setParameter("name", name);
		try
		{
			return (Job) query.getSingleResult();
		}
		catch (Exception e)
		{
		}
		return null;
	}

	private Job get(Long jobId)
	{
		Job found = em.find(Job.class, jobId);
		if (found != null)
		{
			return found;
		}
		return null;
	}

}
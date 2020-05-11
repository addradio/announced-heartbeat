package net.addradio.monitoring.jobs;

import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.addradio.monitoring.entity.EventType;
import net.addradio.monitoring.entity.Job;
import net.addradio.monitoring.entity.LifecycleEvent;

@Singleton
@LocalBean

public class EventLogger
{
	@PersistenceContext(unitName = "monitoring")
	private EntityManager em;

	public LifecycleEvent createLifecycleEvent(Job job, EventType type, String message)
	{
		LifecycleEvent event = new LifecycleEvent(job, type, message);
		em.persist(event);
		return event;
	}

	@SuppressWarnings("unchecked")
	public List<LifecycleEvent> getEvents(Job job)
	{
		String criteriaQuery = "from LifecycleEvent event where event.job=:job";
		Query query = em.createQuery(criteriaQuery);
		query.setParameter("job", job);
		try
		{
			return (List<LifecycleEvent>) query.getResultList();
		}
		catch (Exception e)
		{
		}
		return Collections.emptyList();
	}

}
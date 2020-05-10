package net.addradio.monitoring.jobs;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@LocalBean
public class Registry
{
	@PersistenceContext(unitName = "monitoring")
	EntityManager em;

}
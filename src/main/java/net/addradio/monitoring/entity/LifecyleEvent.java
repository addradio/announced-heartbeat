package net.addradio.monitoring.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lcevents")
public class LifecyleEvent
{
	@Id
	@GeneratedValue
	long id;

	@ManyToOne
	Job job;

	@Lob
	String event;

	@Temporal(TemporalType.TIMESTAMP)
	Date timestamp;
}

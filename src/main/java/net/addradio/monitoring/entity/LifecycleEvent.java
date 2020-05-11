package net.addradio.monitoring.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lcevents")
public class LifecycleEvent
{
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(optional = false)
	private Job job;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EventType type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date timestamp;

	private String message;

	public LifecycleEvent()
	{
	}

	public LifecycleEvent(Job job, EventType type, String message)
	{
		this.job = Objects.requireNonNull(job, "job must not be null");
		this.type = Objects.requireNonNull(type, "type must not be null");
		this.timestamp = new Date();
		this.message = message;
	}

	public long getId()
	{
		return id;
	}

	public Job getJob()
	{
		return job;
	}

	public EventType getType()
	{
		return type;
	}

	public Date getTimestamp()
	{
		return timestamp;
	}

	public String getMessage()
	{
		return message;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LifecycleEvent other = (LifecycleEvent) obj;
		if (id != other.id)
			return false;

		return true;
	}

}

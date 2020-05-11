package net.addradio.monitoring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job
{
	@Id
	@GeneratedValue
	private long jobId;

	@Column(unique = true, nullable = false)
	// TODO 11.5.2020 schneider/fnowotny vergewissern, dass das in der Datenbank gesetzt ist
	private String name;

	public Job()
	{
	}

	public Job(String name)
	{
		this.name = name;
	}

	public long getJobId()
	{
		return jobId;
	}

	public void setJobId(long jobId)
	{
		this.jobId = jobId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (jobId ^ (jobId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Job other = (Job) obj;
		if (jobId != other.jobId)
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else
			if (!name.equals(other.name))
				return false;
		return true;
	}

}

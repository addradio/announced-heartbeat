package net.addradio.monitoring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job
{
	@Id
	@GeneratedValue
	long jobId;

	@Lob
	String name;

}

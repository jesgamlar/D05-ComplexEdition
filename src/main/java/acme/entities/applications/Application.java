
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class Application extends DomainEntity {


	private static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 15)
	private String				referenceNumber;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotBlank
	private String				statement;


	@NotBlank
	private String				skills;

	@NotBlank
	private String				qualifications;

	private boolean				finalMode;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;


	@NotNull
	@Valid
	@ManyToOne(optional = false)

	private Job					job;


	@Transient
	public String getJobTitle() {
		return this.job.getTitle();
	}

}


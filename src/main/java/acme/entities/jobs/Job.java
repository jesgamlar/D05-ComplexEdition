
package acme.entities.jobs;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.duty.Duty;
import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Job extends DomainEntity {

	private static final long				serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 10)
	private String							reference;

	@NotBlank
	private String							title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date							deadline;

	@NotBlank
	private String							description;

	@NotNull
	@Valid
	private Money							salary;

	@URL
	private String							moreInfo;

	private boolean							finalMode;

	@OneToMany(mappedBy = "job")
	private Collection<@Valid Duty>			duties;

	@OneToMany(mappedBy = "job")
	private Collection<@Valid AuditRecords>	auditRecords;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Employer						employer;


	//Derived attributes ------------------------------------------

	@Transient
	public String getStatus() {
		String res;
		if (this.finalMode == true) {
			res = "Published";
		} else {
			res = "Draft";
		}
		return res.toString();
	}

}

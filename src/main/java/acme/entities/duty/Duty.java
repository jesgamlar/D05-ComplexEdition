
package acme.entities.duty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Duty extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	private String				title;

	@NotBlank
	private String				description;

	@Digits(integer = 2, fraction = 2)
	private double				timexWeek;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;
}

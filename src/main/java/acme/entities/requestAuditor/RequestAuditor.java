
package acme.entities.requestAuditor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequestAuditor extends DomainEntity {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				firm;

	@NotBlank
	private String				responsibilityStatement;

	@Valid
	@OneToOne(optional = false)
	private UserAccount			user;

	// Derived attributes -----------------------------------------------------

	@Transient
	public String getUserUsername() {
		return this.user.getUsername();
	}

	// Relationships ----------------------------------------------------------

}

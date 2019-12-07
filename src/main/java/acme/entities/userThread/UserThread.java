
package acme.entities.userThread;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.messageThread.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserThread extends DomainEntity {

	// Serialisation identifier ---------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -----------------------------------------------------------------------------------

	// Relationships --------------------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private MessageThread		messageThread;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		user;

}

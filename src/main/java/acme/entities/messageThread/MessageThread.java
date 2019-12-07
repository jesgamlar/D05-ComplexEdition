
package acme.entities.messageThread;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.userThread.UserThread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThread extends DomainEntity {

	// Serialisation identifier ---------------------------------------------------------------------

	private static final long				serialVersionUID	= 1L;

	// Attributes -----------------------------------------------------------------------------------

	@NotBlank
	private String							title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date							moment;

	// Relationships --------------------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated					starter;

	@OneToMany(mappedBy = "messageThread")
	private Collection<@Valid UserThread>	users;


	@Transient
	public String getStarterUsername() {
		return this.starter.getUserAccount().getUsername();
	}

}

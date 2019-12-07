
package acme.entities.message;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.messageThread.MessageThread;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message extends DomainEntity {

	// Serialisation identifier ---------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -----------------------------------------------------------------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	private String				tags;

	@NotBlank
	private String				body;

	// Relationships --------------------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private MessageThread		messageThread;


	@Transient
	public String getMessageThreadTitle() {
		return this.messageThread.getTitle();
	}

}


package acme.entities.creditCard;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	//Serialisation identifier ----------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	@NotBlank
	private String				holder;

	@NotBlank
	private String				brand;

	@NotBlank
	private String				deadline;

	@NotBlank
	@Pattern(regexp = "5[1-5][0-9]{14}$")
	private String				number;

	@NotBlank
	@Pattern(regexp = "^\\d{3,4}$")
	private String				cvv;

}

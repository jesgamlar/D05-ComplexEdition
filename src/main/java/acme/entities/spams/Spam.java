
package acme.entities.spams;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity {

	//Serialisation identifier---------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------------

	@NotBlank
	private String				englishWords;

	@NotBlank
	private String				spanishWords;

	@Digits(integer = 3, fraction = 2)
	@Min(0)
	@Max(100)
	private double				threshold;

}

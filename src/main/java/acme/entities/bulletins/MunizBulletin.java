
package acme.entities.bulletins;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MunizBulletin extends DomainEntity {

	//Serialisation identifier---------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------------

	@NotBlank
	private String				name;

	@NotBlank
	private String				surname;

	@NotBlank
	@Pattern(regexp = "\\d{8}[A-HJ-NP-TV-Z]")
	private String				idCode;

}

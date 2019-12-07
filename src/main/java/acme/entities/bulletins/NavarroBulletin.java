
package acme.entities.bulletins;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NavarroBulletin extends DomainEntity {

	//Serialisation identifier---------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------------

	@NotBlank
	private String				name;

	@NotNull
	@Positive
	@Max(130)
	private Integer				age;

	@NotNull
	@Positive
	@Max(3)
	private Double				height;

	@NotNull
	@Positive
	@Max(300)
	private Double				weight;

}

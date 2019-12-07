
package acme.entities.companyRecords;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecords extends DomainEntity {

	//Serialization identifier ----------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceo;

	@NotBlank
	private String				activities;

	@NotBlank
	@URL
	private String				website;

	@NotBlank
	@Pattern(regexp = "^(\\+(0?[1-9]|[1-9]\\d) )?(\\(\\d{1,4}\\) )?\\d{6,10}$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	@NotNull
	private Boolean				isIncorporated;

	@Range(min = 0, max = 5)
	private Integer				stars;


	//Derived attributes ------------------------------------------

	@Transient
	public String getIncorporated() {
		StringBuilder res;
		res = new StringBuilder();
		res.append(this.name);
		res.append(", ");
		if (this.isIncorporated == true) {
			res.append("Inc.");
		} else {
			res.append("LLC");
		}
		return res.toString();
	}

}


package acme.entities.banner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends DomainEntity {

	//Serialisation identifier ----------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	@URL
	@NotBlank
	private String				picture;

	@URL
	@NotBlank
	private String				url;

	@NotBlank
	private String				slogan;

	@Column(length = 1024)
	@NotNull
	private CreditCard			creditCard;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;


	@Transient
	public String getCreditcardholder() {
		return this.creditCard.getHolder();
	}

	@Transient
	public String getCreditcardbrand() {
		return this.creditCard.getBrand();
	}

	@Transient
	public String getCreditcarddeadline() {
		return this.creditCard.getDeadline();
	}

	@Transient
	public String getCreditcardnumber() {
		return this.creditCard.getNumber();
	}

	@Transient
	public String getCreditcardcvv() {
		return this.creditCard.getCvv();
	}

}

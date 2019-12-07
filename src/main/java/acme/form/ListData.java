
package acme.form;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListData implements Serializable {

	//Serialisation Identifier------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	Integer						numberAnnouncements;
	Integer						numberCompanies;
	Integer						numberInvestors;
	Money						minRewardRequest;
	Money						maxRewardRequest;
	Money						averageRewardRequest;
	Money						derivationRewardRequest;
	Money						minRewardOffer;
	Money						maxRewardOffer;
	Money						averageRewardOffer;
	Money						derivationRewardOffer;

}


package acme.form;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompaniesInvestorsChart implements Serializable {

	//Serialisation Identifier------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	Map<String, Integer>		companiesSectors;

	Map<String, Integer>		investorsSectors;

}

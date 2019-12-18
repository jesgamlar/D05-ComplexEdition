
package acme.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationsPerDayChart implements Serializable {

	//Serialisation Identifier------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	Map<Date, Integer>			pendingApplications;

	Map<Date, Integer>			acceptedApplications;

	Map<Date, Integer>			rejectedApplications;

	ArrayList<Date>				applicationsDates;

}

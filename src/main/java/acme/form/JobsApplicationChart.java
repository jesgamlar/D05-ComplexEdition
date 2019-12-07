
package acme.form;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobsApplicationChart implements Serializable {

	//Serialisation Identifier------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	Map<String, Integer>		jobsStatus;

	Map<String, Integer>		applicationStatus;

}

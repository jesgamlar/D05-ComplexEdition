
package acme.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDataEmployerWorker implements Serializable {

	//Serialisation Identifier------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	Integer						jobsPerEmployer;
	Integer						applicationsPerEmployer;
	Integer						applicationsPerWorker;

}

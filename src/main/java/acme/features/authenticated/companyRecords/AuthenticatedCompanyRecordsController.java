
package acme.features.authenticated.companyRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/company-records")
public class AuthenticatedCompanyRecordsController extends AbstractController<Authenticated, CompanyRecords> {

	//Internal state -------------------------------------------

	@Autowired
	private AuthenticatedCompanyRecordsListService	listService;

	@Autowired
	private AuthenticatedCompanyRecordsShowService	showService;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}

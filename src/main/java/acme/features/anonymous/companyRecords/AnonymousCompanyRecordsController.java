
package acme.features.anonymous.companyRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.companyRecords.CompanyRecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/company-records")
public class AnonymousCompanyRecordsController extends AbstractController<Anonymous, CompanyRecords> {

	//Internal state -------------------------------------------

	@Autowired
	private AnonymousCompanyRecordsListService	listService;

	@Autowired
	private AnonymousCompanyRecordsShowService	showService;

	@Autowired
	private AnonymousCompanyRecordsTopService	listTopService;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_TOP, BasicCommand.LIST, this.listTopService);
	}

}

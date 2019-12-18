
package acme.features.auditor.auditRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditRecords.AuditRecords;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-records")
public class AuditorAuditRecordsController extends AbstractController<Auditor, AuditRecords> {

	//Internal state -------------------------------------------

	@Autowired
	private AuditorAuditRecordsListMineService	listMineService;

	@Autowired
	private AuditorAuditRecordsShowService		showService;

	@Autowired
	private AuditorAuditRecordsCreateService	createService;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}

}

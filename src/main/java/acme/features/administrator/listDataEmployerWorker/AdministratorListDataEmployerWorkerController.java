
package acme.features.administrator.listDataEmployerWorker;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.form.ListDataEmployerWorker;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/list-data-employer-worker/")
public class AdministratorListDataEmployerWorkerController extends AbstractController<Administrator, ListDataEmployerWorker> {

	//Internal state -------------------------------------------

	@Autowired
	private AdministratorListDataEmployerWorkerService listMine;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.SHOW, this.listMine);
	}

}

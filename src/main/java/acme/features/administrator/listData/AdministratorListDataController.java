
package acme.features.administrator.listData;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.form.ListData;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/list-data/")
public class AdministratorListDataController extends AbstractController<Administrator, ListData> {

	//Internal state -------------------------------------------

	@Autowired
	private AdministratorListDataService listMine;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.SHOW, this.listMine);
	}

}

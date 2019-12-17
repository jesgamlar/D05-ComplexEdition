
package acme.features.authenticated.userThread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.userThread.UserThread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/user-thread")
public class AuthenticatedUserThreadController extends AbstractController<Authenticated, UserThread> {

	//Internal state -------------------------------------------

	@Autowired
	private AuthenticatedUserThreadListMineService	listMineService;

	@Autowired
	private AuthenticatedUserThreadShowService		showService;

	@Autowired
	private AuthenticatedUserThreadCreateService	createService;

	@Autowired
	private AuthenticatedUserThreadDeleteService	deleteService;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}

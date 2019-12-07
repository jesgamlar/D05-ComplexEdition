
package acme.features.provider.requestEntity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requestEntity.RequestEntity;
import acme.entities.roles.Provider;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/provider/request-entity")
public class ProviderRequestEntityController extends AbstractController<Provider, RequestEntity> {

	//Internal state -------------------------------------------

	@Autowired
	private ProviderRequestEntityListService	listService;

	@Autowired
	private ProviderRequestEntityShowService	showService;

	@Autowired
	private ProviderRequestEntityCreateService	createService;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}

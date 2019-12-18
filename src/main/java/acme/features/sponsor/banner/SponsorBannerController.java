
package acme.features.sponsor.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banner.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/commercial-banner")
public class SponsorBannerController extends AbstractController<Sponsor, CommercialBanner> {

	//Internal state -------------------------------------------

	@Autowired
	private SponsorBannerListMineService	listMineService;

	@Autowired
	private SponsorBannerShowService		showService;

	@Autowired
	private SponsorBannerCreateService		createService;

	@Autowired
	private SponsorBannerUpdateService		updateService;

	@Autowired
	private SponsorBannerDeleteService		deleteService;


	//Constructors ---------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);

		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}

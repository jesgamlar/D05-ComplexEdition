
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	//Internal state --------------------------------------------------

	@Autowired
	private EmployerApplicationRepository repository;


	//AbstractShowService<Administrator, Announcement> interface ------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		Principal principal;

		int appId = request.getModel().getInteger("id");
		Application app = this.repository.findOneApplicationById(appId);
		Worker worker = app.getWorker();
		principal = request.getPrincipal();
		result = app.isFinalMode() || !app.isFinalMode() && worker.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "moment", "statement");
		request.unbind(entity, model, "skills", "qualifications", "finalMode");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;

	}

}

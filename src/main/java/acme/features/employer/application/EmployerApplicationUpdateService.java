
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.ApplicationStatus;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {
	//Internal state --------------------------------------------------------------

	@Autowired
	EmployerApplicationRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Principal p = request.getPrincipal();
		int id = p.getActiveRoleId();
		Collection<Application> applicationsEmployer = this.repository.findManyByEmployerId(id);
		int idApplication = request.getModel().getInteger("id");
		Application application = this.repository.findOneApplicationById(idApplication);

		return applicationsEmployer.contains(application);
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "moment", "statement", "skills", "qualifications", "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result = new Application();
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (request.getModel().getAttribute("acceptReject").equals("reject")) {
			if (!errors.hasErrors("justification")) {
				errors.state(request, !entity.getJustification().isEmpty(), "justification", "employer.application.form.error.reject");
			}
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getAttribute("acceptReject").equals("accept")) {
			entity.setStatus(ApplicationStatus.ACCEPTED);
		} else if (request.getModel().getAttribute("acceptReject").equals("reject")) {
			entity.setStatus(ApplicationStatus.REJECTED);
		}

		this.repository.save(entity);
	}
}

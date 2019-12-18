
package acme.features.employer.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	//Internal state --------------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "finalMode", "employer");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "reference", "deadline", "description", "salary", "moreInfo");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Job result;

		result = new Job();

		Principal principal = request.getPrincipal();
		Employer e = this.repository.findEmployerById(principal.getActiveRoleId());
		result.setEmployer(e);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());
			errors.state(request, entity.getDeadline().after(currentDate), "deadline", "employer.job.form.error.deadline");
		}

		if (!errors.hasErrors("reference")) {
			List<String> referenceCodes = this.repository.findReferences();
			errors.state(request, !referenceCodes.contains(entity.getReference()), "reference", "employer.job.form.error.reference");
		}
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		entity.setFinalMode(false);

		this.repository.save(entity);
	}

}

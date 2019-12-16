
package acme.features.employer.job;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duty.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {
	//Internal state --------------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

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

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "reference", "deadline", "description", "salary", "moreInfo");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result = new Job();
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors()) {
			List<Integer> ids = this.repository.findJobsWithApplications();
			int idThisJob = request.getModel().getInteger("id");
			errors.state(request, !ids.contains(idThisJob), "reference", "employer.job.form.error.delete");
		}

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		int idThisJob = request.getModel().getInteger("id");
		Collection<Duty> thisJobDuties = this.repository.findManyDutiesByJobId(idThisJob);
		if (!thisJobDuties.isEmpty()) {
			for (Duty d : thisJobDuties) {
				this.repository.delete(d);
			}
		}
		this.repository.delete(entity);
	}

}

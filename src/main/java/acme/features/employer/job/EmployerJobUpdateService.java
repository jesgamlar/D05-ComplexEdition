
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
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {
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

		request.unbind(entity, model, "title", "reference", "deadline", "description", "salary", "moreInfo", "finalMode");
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

		if (entity.getStatus() == "Published") {
			if (!errors.hasErrors("description")) {
				int threshold = this.repository.findThreshold();
				String[] spanishWords = this.repository.findSpanishWords().split(", ");
				String[] englishWords = this.repository.findEnglishWords().split(", ");
				int numberWordsDescription = entity.getDescription().split("\\s|\\.").length;

				int spamWords = 0;
				for (String s : spanishWords) {
					if (entity.getDescription().contains(s)) {
						spamWords++;
					}
				}
				for (String s : englishWords) {
					if (entity.getDescription().contains(s)) {
						spamWords++;
					}
				}

				errors.state(request, spamWords / numberWordsDescription * 100 <= threshold, "description", "employer.job.form.error.spamDescription");

			}

			if (!errors.hasErrors()) {
				int id = request.getModel().getInteger("id");
				List<Double> ls = this.repository.findTimexWeekThisJob(id);
				Double count = 0.;
				for (Double c : ls) {
					count += c;
				}
				errors.state(request, count == 100, "reference", "employer.job.form.error.no100");
			}

			if (!errors.hasErrors()) {
				int id = request.getModel().getInteger("id");
				Collection<Duty> ls = this.repository.findManyDutiesByJobId(id);
				errors.state(request, !ls.isEmpty(), "reference", "employer.job.form.error.noDuties");
			}

		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}

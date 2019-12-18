
package acme.features.employer.job;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duty.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
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

		Principal p = request.getPrincipal();
		int id = p.getActiveRoleId();
		Collection<Job> jobsEmployer = this.repository.findManyJobByEmployerId(id);
		int idJob = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(idJob);
		boolean isPublished = this.repository.findOneJobStatusById(idJob);
		boolean res = !isPublished && jobsEmployer.contains(job);

		return res;
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

		if (!errors.hasErrors("reference")) {
			int id = request.getModel().getInteger("id");
			String oldReference = this.repository.findOneJobReferenceById(id);
			if (!oldReference.equals(request.getModel().getString("reference"))) {
				List<String> referenceCodes = this.repository.findReferences();
				errors.state(request, !referenceCodes.contains(entity.getReference()), "reference", "employer.job.form.error.reference");
			}
		}

		if (!errors.hasErrors("deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());
			errors.state(request, entity.getDeadline().after(currentDate), "deadline", "employer.job.form.error.deadline");
		}

		if (entity.getStatus() == "Published") {
			if (!errors.hasErrors("description")) {
				errors.state(request, !entity.getDescription().isEmpty(), "description", "employer.job.form.error.emptyDescription");

				double threshold = this.repository.findThreshold();
				String[] spanishWords = this.repository.findSpanishWords().split(", ");
				String[] englishWords = this.repository.findEnglishWords().split(", ");
				double numberWordsDescription = entity.getDescription().split("\\s|\\.|\\,").length;
				double numberWordsTitle = entity.getTitle().split("\\s|\\.|\\,").length;

				double spamWords = 0.;
				for (String s : spanishWords) {
					if (entity.getDescription().contains(s)) {
						spamWords++;
					}
					if (entity.getTitle().contains(s)) {
						spamWords++;
					}
				}
				for (String s : englishWords) {
					if (entity.getDescription().contains(s)) {
						spamWords++;
					}
					if (entity.getTitle().contains(s)) {
						spamWords++;
					}
				}

				errors.state(request, spamWords / (numberWordsDescription + numberWordsTitle) * 100 <= threshold, "description", "employer.job.form.error.spamDescription");

			}

			int id = request.getModel().getInteger("id");

			if (!errors.hasErrors("reference")) {
				Collection<Duty> listDuties = this.repository.findManyDutiesByJobId(id);
				errors.state(request, !listDuties.isEmpty(), "reference", "employer.job.form.error.noDuties");
			}

			if (!errors.hasErrors("reference")) {
				List<Double> listTime = this.repository.findTimexWeekThisJob(id);
				Double count = 0.;
				for (Double c : listTime) {
					count += c;
				}
				errors.state(request, count == 100, "reference", "employer.job.form.error.no100");
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

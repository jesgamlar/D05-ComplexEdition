
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.ApplicationStatus;
import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	//Internal state --------------------------------------------------------------

	@Autowired
	WorkerApplicationRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "referenceNumber", "moment", "status", "worker", "job");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "statement", "skills", "qualifications");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		//Status
		ApplicationStatus status = ApplicationStatus.PENDING;
		entity.setStatus(status);

		//Worker
		Principal principal = request.getPrincipal();
		Worker worker = this.repository.findWorkerById(principal.getActiveRoleId());
		entity.setWorker(worker);

		//Job
		String jobIdString = request.getServletRequest().getQueryString().split("jobid=")[1];

		int jobId = Integer.parseInt(jobIdString);

		Job job = this.repository.findJobById(jobId);
		entity.setJob(job);

		//Moment
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		//Reference number
		Employer employer = job.getEmployer();
		String employerId = "" + employer.getId();

		String workerId = "" + worker.getId();

		while (employerId.length() < 3) {
			employerId = "0" + employerId;
		}

		while (workerId.length() < 3) {
			workerId = "0" + workerId;
		}

		while (jobIdString.length() < 3) {
			jobIdString = "0" + jobIdString;
		}

		String emp = "E" + employerId.substring(employerId.length() - 3, employerId.length());
		String wor = "W" + workerId.substring(workerId.length() - 3, workerId.length());
		String jobb = "J" + jobIdString.substring(jobIdString.length() - 3, jobIdString.length());

		String rNumber = emp + "-" + wor + ":" + jobb;

		Collection<Application> applicationSameRNumber = this.repository.findManyByRnumber(rNumber);

		String rNumberFinal = rNumber + applicationSameRNumber.size();

		entity.setReferenceNumber(rNumberFinal);

		this.repository.save(entity);
	}

}

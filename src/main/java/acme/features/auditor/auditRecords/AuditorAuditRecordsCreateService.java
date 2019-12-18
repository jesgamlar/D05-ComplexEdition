
package acme.features.auditor.auditRecords;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service

public class AuditorAuditRecordsCreateService implements AbstractCreateService<Auditor, AuditRecords> {

	@Autowired

	AuditorAuditRecordRepository repository;


	@Override

	public boolean authorise(final Request<AuditRecords> request) {

		assert request != null;

		return true;

	}

	@Override

	public void bind(final Request<AuditRecords> request, final AuditRecords entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

		request.bind(entity, errors, "moment", "job", "auditor", "status");

	}

	@Override

	public void unbind(final Request<AuditRecords> request, final AuditRecords entity, final Model model) {

		assert request != null;

		assert entity != null;

		assert model != null;

		request.unbind(entity, model, "title", "body");

	}

	@Override

	public AuditRecords instantiate(final Request<AuditRecords> request) {

		AuditRecords result;

		result = new AuditRecords();

		Principal principal = request.getPrincipal();
		Auditor auditor = this.repository.findAuditorById(principal.getAccountId());

		result.setAuditor(auditor);

		String jobIdString = request.getServletRequest().getQueryString().split("jobid=")[1];

		int jobId = Integer.parseInt(jobIdString);

		Job job = this.repository.findJobById(jobId);
		result.setJob(job);
		result.setStatus(false);

		return result;

	}

	@Override

	public void validate(final Request<AuditRecords> request, final AuditRecords entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

	}

	@Override

	public void create(final Request<AuditRecords> request, final AuditRecords entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}

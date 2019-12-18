
package acme.features.auditor.auditRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordsUpdateService implements AbstractUpdateService<Auditor, AuditRecords> {
	//Internal state --------------------------------------------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<AuditRecords> request) {
		assert request != null;

		Principal p = request.getPrincipal();
		int id = p.getActiveRoleId();

		int auditId = request.getModel().getInteger("id");
		AuditRecords auditRecord = this.repository.findOneAuditById(auditId);

		Auditor auditor = this.repository.findAuditorById(id);

		boolean res = !auditRecord.getStatus() && auditRecord.getAuditor().equals(auditor);

		return res;
	}

	@Override
	public void bind(final Request<AuditRecords> request, final AuditRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "job", "auditor");
	}

	@Override
	public void unbind(final Request<AuditRecords> request, final AuditRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "status");
	}

	@Override
	public AuditRecords findOne(final Request<AuditRecords> request) {
		assert request != null;

		AuditRecords result = new AuditRecords();
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditById(id);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecords> request, final AuditRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AuditRecords> request, final AuditRecords entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}

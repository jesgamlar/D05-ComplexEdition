
package acme.features.auditor.AuditorAuditRecordCreateService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service

public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired

	AuditorAuditRecordRepository repository;


	@Override

	public boolean authorise(final Request<AuditRecord> request) {

		assert request != null;

		return true;

	}

	@Override

	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override

	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {

		assert request != null;

		assert entity != null;

		assert model != null;

		request.unbind(entity, model, "title", "moreInfo", "moment", "body", "job", "auditor");

	}

	@Override

	public AuditRecord instantiate(final Request<AuditRecord> request) {

		AuditRecord result;

		result = new Offer();

		return result;

	}

	@Override

	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {

		assert request != null;

		assert entity != null;

		assert errors != null;

		

	}

	@Override

	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}

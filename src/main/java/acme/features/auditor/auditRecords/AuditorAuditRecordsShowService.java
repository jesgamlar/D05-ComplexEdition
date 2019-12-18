
package acme.features.auditor.auditRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordsShowService implements AbstractShowService<Auditor, AuditRecords> {

	//Internal state --------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository repository;


	//AbstractShowService<Administrator, Announcement> interface ------

	@Override
	public boolean authorise(final Request<AuditRecords> request) {
		assert request != null;

		return true;

	}

	@Override
	public void unbind(final Request<AuditRecords> request, final AuditRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "moment", "body");
	}

	@Override
	public AuditRecords findOne(final Request<AuditRecords> request) {
		assert request != null;

		AuditRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditById(id);

		return result;

	}

}

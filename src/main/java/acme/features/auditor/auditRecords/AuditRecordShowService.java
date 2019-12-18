
package acme.features.authenticated.auditRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuditRecordShowService implements AbstractShowService<Authenticated, AuditRecords> {

	//Internal state --------------------------------------------------

	@Autowired
	private AuditRecordRepository repository;


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

		request.unbind(entity, model, "title", "moreInformation", "moment", "body");
	}

	@Override
	public AuditRecords findOne(final Request<AuditRecords> request) {
		assert request != null;

		AuditRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;

	}

}

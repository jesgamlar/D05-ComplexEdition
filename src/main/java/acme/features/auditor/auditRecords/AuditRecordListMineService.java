
package acme.features.authenticated.auditRecords;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuditRecordListMineService implements AbstractListService<Authenticated, AuditRecords> {

	@Autowired
	AuditRecordRepository repository;


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
	public Collection<AuditRecords> findMany(final Request<AuditRecords> request) {
		assert request != null;

		Collection<AuditRecords> result;

		int id = request.getModel().getInteger("id");
		result = this.repository.findManyMine(id);
		result = result.stream().filter(x -> x.getMoreInfo() && x.getAuditor().getUserAccount().getId() != request.getPrincipal().getAccountId()).collect(Collectors.toList());

		return result;
	}

}

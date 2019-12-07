
package acme.features.administrator.companyRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCompanyRecordsCreateService implements AbstractCreateService<Administrator, CompanyRecords> {

	@Autowired
	AdministratorCompanyRecordsRepository repository;


	@Override
	public boolean authorise(final Request<CompanyRecords> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CompanyRecords> request, final CompanyRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "incorporated");
	}

	@Override
	public void unbind(final Request<CompanyRecords> request, final CompanyRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "ceo", "activities", "website", "phone", "email", "isIncorporated", "stars");
	}

	@Override
	public CompanyRecords instantiate(final Request<CompanyRecords> request) {
		CompanyRecords result;

		result = new CompanyRecords();

		return result;
	}

	@Override
	public void validate(final Request<CompanyRecords> request, final CompanyRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<CompanyRecords> request, final CompanyRecords entity) {
		this.repository.save(entity);
	}

}

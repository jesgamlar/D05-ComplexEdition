
package acme.features.administrator.companyInvestorRecordsDashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.form.CompaniesInvestorsChart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCompanyInvestorsRecordsDashboardChartService implements AbstractShowService<Administrator, CompaniesInvestorsChart> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorCompanyInvestorsRecordsDashboardRepository repository;


	//AbstractListService<Anonymous, CompanyRecords> interface ------

	@Override
	public boolean authorise(final Request<CompaniesInvestorsChart> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CompaniesInvestorsChart> request, final CompaniesInvestorsChart formObject, final Model model) {
		assert request != null;
		assert formObject != null;
		assert model != null;

		request.unbind(formObject, model, "companiesSectors", "investorsSectors");
	}

	@Override
	public CompaniesInvestorsChart findOne(final Request<CompaniesInvestorsChart> request) {
		assert request != null;

		CompaniesInvestorsChart result = new CompaniesInvestorsChart();

		Map<String, Integer> mapCompanies = new HashMap<String, Integer>();
		Map<String, Integer> mapSectors = new HashMap<String, Integer>();

		for (int i = 0; i < this.repository.companies().size(); i++) {
			mapCompanies.put(this.repository.companiesSectors().get(i), this.repository.companies().get(i));
		}

		for (int i = 0; i < this.repository.investors().size(); i++) {
			mapSectors.put(this.repository.investorsSectors().get(i), this.repository.investors().get(i));
		}

		for (int i = 0; i < this.repository.investors().size(); i++) {
			if (!mapCompanies.containsKey(this.repository.investorsSectors().get(i))) {
				mapCompanies.put(this.repository.investorsSectors().get(i), 0);
			}

		}

		for (int i = 0; i < this.repository.companies().size(); i++) {
			if (!mapSectors.containsKey(this.repository.companiesSectors().get(i))) {
				mapSectors.put(this.repository.companiesSectors().get(i), 0);
			}

		}

		result.setCompaniesSectors(mapCompanies);
		result.setInvestorsSectors(mapSectors);

		return result;
	}

}

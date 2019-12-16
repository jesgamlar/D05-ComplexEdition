
package acme.features.administrator.applicationsPerDayDashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.form.ApplicationsPerDayChart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorApplicationsPerDayDashboardChartService implements AbstractShowService<Administrator, ApplicationsPerDayChart> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorApplicationsPerDayDashboardRepository repository;


	//AbstractListService<Anonymous, CompanyRecords> interface ------

	@Override
	public boolean authorise(final Request<ApplicationsPerDayChart> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<ApplicationsPerDayChart> request, final ApplicationsPerDayChart formObject, final Model model) {
		assert request != null;
		assert formObject != null;
		assert model != null;

		request.unbind(formObject, model, "pendingApplications", "acceptedApplications", "rejectedApplications", "applicationsDates");
	}

	@Override
	public ApplicationsPerDayChart findOne(final Request<ApplicationsPerDayChart> request) {
		assert request != null;

		ApplicationsPerDayChart result = new ApplicationsPerDayChart();

		Map<Date, Integer> mapPending = new HashMap<Date, Integer>();
		Map<Date, Integer> mapAccepted = new HashMap<Date, Integer>();
		Map<Date, Integer> mapRejected = new HashMap<Date, Integer>();

		for (Date date : this.repository.applicationDates()) {
			mapPending.put(date, this.repository.pendingApplications(date));
		}

		for (Date date : this.repository.applicationDates()) {
			mapAccepted.put(date, this.repository.acceptedApplications(date));
		}

		for (Date date : this.repository.applicationDates()) {
			mapRejected.put(date, this.repository.rejectedApplications(date));
		}

		ArrayList<Date> applicationDates = this.repository.applicationDates();
		Collections.sort(applicationDates);

		result.setPendingApplications(mapPending);
		result.setAcceptedApplications(mapAccepted);
		result.setRejectedApplications(mapRejected);
		result.setApplicationsDates(applicationDates);

		return result;
	}

}

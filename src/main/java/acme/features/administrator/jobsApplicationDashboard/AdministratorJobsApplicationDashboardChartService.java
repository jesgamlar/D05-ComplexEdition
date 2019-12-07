
package acme.features.administrator.jobsApplicationDashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.form.JobsApplicationChart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorJobsApplicationDashboardChartService implements AbstractShowService<Administrator, JobsApplicationChart> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorJobsApplicationDashboardRepository repository;


	//AbstractListService<Anonymous, CompanyRecords> interface ------

	@Override
	public boolean authorise(final Request<JobsApplicationChart> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<JobsApplicationChart> request, final JobsApplicationChart formObject, final Model model) {
		assert request != null;
		assert formObject != null;
		assert model != null;

		request.unbind(formObject, model, "jobsStatus", "applicationStatus");
	}

	@Override
	public JobsApplicationChart findOne(final Request<JobsApplicationChart> request) {
		assert request != null;

		JobsApplicationChart result = new JobsApplicationChart();

		Map<String, Integer> mapJobs = new HashMap<String, Integer>();
		Map<String, Integer> mapApplication = new HashMap<String, Integer>();

		for (int i = 0; i < this.repository.jobs().size(); i++) {
			mapJobs.put(this.repository.jobsStatus().get(i), this.repository.jobs().get(i));
		}

		for (int i = 0; i < this.repository.application().size(); i++) {
			mapApplication.put(this.repository.applicationStatus().get(i), this.repository.application().get(i));
		}

		for (int i = 0; i < this.repository.application().size(); i++) {
			if (!mapJobs.containsKey(this.repository.applicationStatus().get(i))) {
				mapJobs.put(this.repository.applicationStatus().get(i), 0);
			}

		}

		for (int i = 0; i < this.repository.jobs().size(); i++) {
			if (!mapApplication.containsKey(this.repository.jobsStatus().get(i))) {
				mapApplication.put(this.repository.jobsStatus().get(i), 0);
			}

		}

		result.setJobsStatus(mapJobs);
		result.setApplicationStatus(mapApplication);

		return result;
	}

}

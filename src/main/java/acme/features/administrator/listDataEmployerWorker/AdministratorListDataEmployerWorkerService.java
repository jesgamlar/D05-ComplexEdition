
package acme.features.administrator.listDataEmployerWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.form.ListDataEmployerWorker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorListDataEmployerWorkerService implements AbstractShowService<Administrator, ListDataEmployerWorker> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorListDataEmployerWorkerRepository repository;


	//AbstractListService<Authenticated, Announcement> interface ------

	@Override
	public boolean authorise(final Request<ListDataEmployerWorker> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<ListDataEmployerWorker> request, final ListDataEmployerWorker entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "jobsPerEmployer", "applicationsPerEmployer", "applicationsPerWorker");

	}

	@Override
	public ListDataEmployerWorker findOne(final Request<ListDataEmployerWorker> request) {
		assert request != null;

		ListDataEmployerWorker result = new ListDataEmployerWorker();

		result.setJobsPerEmployer(this.repository.jobsEmployer());
		result.setApplicationsPerEmployer(this.repository.applicationsEmployer());
		result.setApplicationsPerWorker(this.repository.applicationsWorker());

		return result;
	}

}

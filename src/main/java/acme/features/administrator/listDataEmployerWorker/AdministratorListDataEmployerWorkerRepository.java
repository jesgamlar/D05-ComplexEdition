
package acme.features.administrator.listDataEmployerWorker;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorListDataEmployerWorkerRepository extends AbstractRepository {

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Integer jobsEmployer();

	@Query("select avg(select count(a) from Application a where a.job.id in (select j.id from Job j where j.employer.id = e.id)) from Employer e")
	Integer applicationsEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Integer applicationsWorker();

}

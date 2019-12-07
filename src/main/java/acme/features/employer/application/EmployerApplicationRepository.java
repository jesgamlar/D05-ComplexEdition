
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findManyByJobId(int jobId);

	@Query("select a from Application a where a.job.id in (select j.id from Job j where j.employer.id = ?1)")
	Collection<Application> findManyByEmployerId(int employerId);

}

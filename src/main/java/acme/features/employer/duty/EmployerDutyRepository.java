
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duty.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);

	@Query("select d.job.id from Duty d where d.id = ?1")
	Integer findOneJobIdByDutyId(int id);

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyDutiesByJobId(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j.finalMode from Job j where j.id = ?1")
	boolean findOneJobStatus(int id);

	@Query("select d.job.id from Duty d where d.id = ?1")
	Integer findOneJobByDutyId(int dutyId);

}

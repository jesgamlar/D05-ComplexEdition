
package acme.features.auditor.jobs;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	//	@Query("select a from Job a where (select d.job.id from AuditRecords d where d.auditor.id != ?1) contains a.id ")
	//	Collection<Job> findManyMine(int id);
	//"select a from Job a where a.auditRecords.auditor.id != ?=1 "
	@Query("select a from Job a where a.id in (select d.job.id from AuditRecords d where d.auditor.id = ?1)")
	Collection<Job> findManyMine(int id);

	@Query("select a from Job a where a.id not in (select d.job.id from AuditRecords d where d.auditor.id = ?1)")
	Collection<Job> findManyNotMine(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

}

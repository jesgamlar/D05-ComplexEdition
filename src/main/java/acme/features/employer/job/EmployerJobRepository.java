
package acme.features.employer.job;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duty.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select e from Employer e where e.id = ?1")
	Employer findEmployerById(int employerId);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyJobByEmployerId(int employerId);

	@Query("select a.job.id from Application a")
	List<Integer> findJobsWithApplications();

	@Query("select s.threshold from Spam s")
	double findThreshold();

	@Query("select s.spanishWords from Spam s")
	String findSpanishWords();

	@Query("select s.englishWords from Spam s")
	String findEnglishWords();

	@Query("select j.reference from Job j")
	List<String> findReferences();

	@Query("select d.timexWeek from Duty d where d.job.id = ?1")
	List<Double> findTimexWeekThisJob(int jobId);

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyDutiesByJobId(int id);

	@Query("select j.reference from Job j where j.id = ?1")
	String findOneJobReferenceById(int id);

	@Query("select j.finalMode from Job j where j.id = ?1")
	boolean findOneJobStatusById(int id);
}

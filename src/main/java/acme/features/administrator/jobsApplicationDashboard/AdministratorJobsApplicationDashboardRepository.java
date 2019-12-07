/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.jobsApplicationDashboard;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorJobsApplicationDashboardRepository extends AbstractRepository {

	@Query("select  finalMode from Job group by finalMode order by finalMode asc")
	ArrayList<String> jobsStatus();

	@Query("select  finalMode from Application group by finalMode order by finalMode asc")
	ArrayList<String> applicationStatus();

	@Query("select  count(finalMode) from Job group by finalMode order by finalMode asc")
	ArrayList<Integer> jobs();

	@Query("select  count(finalMode) from Application group by finalMode order by finalMode asc")
	ArrayList<Integer> application();

}

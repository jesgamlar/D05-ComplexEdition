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

package acme.features.administrator.applicationsPerDayDashboard;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorApplicationsPerDayDashboardRepository extends AbstractRepository {

	@Query("select count(*) from Application a where a.status='0' and day(a.moment) = day(?1)")
	Integer pendingApplications(Date date);

	@Query("select count(*) from Application a where a.status='1' and day(a.moment) = day(?1)")
	Integer acceptedApplications(Date date);

	@Query("select count(*) from Application a where a.status='2' and day(a.moment) = day(?1)")
	Integer rejectedApplications(Date date);

	//@Query("select  count(a) from Application a where a.status='accepted' group by day(a.moment) having a.moment < current_date() -28 order by moment asc")
	//ArrayList<Integer> acceptedApplications();

	//@Query("select  count(a) from Application a where a.status='rejected' group by day(a.moment) having a.moment < current_date() -28 order by moment asc")
	//ArrayList<Integer> rejectedApplications();

	//@Query("select  day(a.moment) from Application a where a.status='pending' group by day(a.moment) having a.moment < current_date() -28 order by moment asc")
	//ArrayList<String> pendingDates();

	//@Query("select  day(a.moment) from Application a where a.status='accepted' group by day(a.moment) having a.moment < current_date() -28 order by moment asc")
	//ArrayList<String> acceptedDates();

	@Query("select  a.moment from Application a group by day(a.moment) having a.moment  >= (subdate(curdate(),'28/00/00')) order by moment asc")
	ArrayList<Date> applicationDates();

}

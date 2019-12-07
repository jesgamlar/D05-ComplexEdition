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

package acme.features.administrator.companyInvestorRecordsDashboard;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCompanyInvestorsRecordsDashboardRepository extends AbstractRepository {

	@Query("select  sector from CompanyRecords group by sector order by sector asc")
	ArrayList<String> companiesSectors();

	@Query("select  sector from InvestorRecord group by sector order by sector asc")
	ArrayList<String> investorsSectors();

	@Query("select  count(sector) from CompanyRecords group by sector order by sector asc")
	ArrayList<Integer> companies();

	@Query("select  count(sector) from InvestorRecord group by sector order by sector asc")
	ArrayList<Integer> investors();

}

<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
		
			<acme:menu-suboption code="master.menu.anonymous.listJesus" action="/anonymous/gamez-bulletin/list"/>

			<acme:menu-suboption code="master.menu.anonymous.listJoseManuel" action="/anonymous/sanchez-bulletin/list"/>
			
			<acme:menu-suboption code="master.menu.anonymous.listJavier" action="/anonymous/navarro-bulletin/list"/>

			<acme:menu-suboption code="master.menu.anonymous.listJose" action="/anonymous/martin-bulletin/list"/>
			
			<acme:menu-suboption code="master.menu.anonymous.listIsaac" action="/anonymous/muniz-bulletin/list"/>
			
			<acme:menu-suboption code="master.menu.anonymous.listInvestorRecord" action="/anonymous/investor-record/list"/>
			
			<acme:menu-suboption code="master.menu.anonymous.listCompaniesRecord" action="/anonymous/company-records/list"/>
			
			<acme:menu-suboption code="master.menu.anonymous.listAnnouncements" action="/anonymous/announcement/list"/>
			
			<acme:menu-separator/>
			
			<acme:menu-suboption code="master.menu.anonymous.listTopCompanies" action="/anonymous/company-records/list-top"/>
			
			<acme:menu-suboption code="master.menu.anonymous.listTopInvestors" action="/anonymous/investor-record/list-top"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.listJobs" action="/authenticated/job/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listMessageThreads" action="/authenticated/message-thread/list-mine"/>
			<acme:menu-suboption code="master.menu.authenticated.listAnnouncement" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listChallenge" action="/authenticated/challenge/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listCompaniesRecord" action="/authenticated/company-records/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listInvestorRecord" action="/authenticated/investor-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listOffers" action="/authenticated/offer/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listRequests" action="/authenticated/request-entity/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.listJobs" action="/employer/job/list-mine"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.commercial-banner" action="/administrator/commercial-banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.commercial-banner.create" action="/administrator/commercial-banner/create"/>
			<acme:menu-suboption code="master.menu.administrator.non-commercial-banner" action="/administrator/non-commercial-banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.non-commercial-banner.create" action="/administrator/non-commercial-banner/create"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.listAnnouncement" action="/administrator/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.listAnnouncement.create" action="/administrator/announcement/create"/>
			<acme:menu-suboption code="master.menu.administrator.investorRecord" action="/administrator/investor-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.investorRecord.create" action="/administrator/investor-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.spam" action="/administrator/spam/show"/>
			<acme:menu-suboption code="master.menu.administrator.companyRecords" action="/administrator/company-records/list"/>
			<acme:menu-suboption code="master.menu.administrator.companyRecords.create" action="/administrator/company-records/create"/>
			<acme:menu-suboption code="master.menu.administrator.listChallenge" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.listChallenge.create" action="/administrator/challenge/create"/>
     		<acme:menu-suboption code="master.menu.administrator.companyRecordsChart" action="/administrator/companies-investors-chart/chart"/>
      		<acme:menu-suboption code="master.menu.administrator.jobsApplicationsChart" action="/administrator/jobs-application-chart/chart"/>
      		<acme:menu-suboption code="master.menu.administrator.listData" action="/administrator/list-data/list-mine"/>
      		<acme:menu-suboption code="master.menu.administrator.listDataEmployerWorker" action="/administrator/list-data-employer-worker/list-mine"/>
      
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.application.list" action="/worker/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.banner.list" action="/sponsor/commercial-banner/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job.list" action="/employer/job/list-mine"/>
			<acme:menu-suboption code="master.menu.employer.application.list" action="/employer/application/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.request.create" action="/provider/request-entity/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.offer.create" action="/consumer/offer/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.job.list-mine" action="/auditor/job/list-mine"/>
			<acme:menu-suboption code="master.menu.auditor.job.list-not-mine" action="/auditor/job/list-not-mine"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/auditor/create" access="!hasRole('Auditor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.job.form.label.reference" path="reference"/>

	<%-- <acme:form-checkbox code="authenticated.job.form.label.status" path="finalMode"/> --%>

	<acme:form-textbox code="authenticated.job.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="authenticated.job.form.label.salary" path="salary"/>
	<acme:form-url code="authenticated.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="authenticated.job.form.label.description" path="description"/>
	
	
	<acme:form-submit test="${principal.hasRole('acme.entities.roles.Worker') == true}"
		code="worker.application.form.button.create"
		action="/worker/application/create?jobid=${id}"
		method="get"/>
		
	<acme:form-submit test="${principal.hasRole('acme.entities.roles.Auditor') == true}"
		code="auditor.audit-record.form.button.create"
		action="/auditor/audit-records/create?jobid=${id}"
		method="get"/>
		
	<acme:form-submit code='authenticated.job.form.button.listDuties'
	 method='get' action='/authenticated/duty/list?id=${id}' />
	
	<acme:form-submit code='authenticated.job.form.button.listAuditRecords'
	 method='get' action='/authenticated/audit-records/list-mine?id=${id}' />
	 
	 <acme:form-return code="authenticated.job.form.button.return"/>
	

</acme:form>



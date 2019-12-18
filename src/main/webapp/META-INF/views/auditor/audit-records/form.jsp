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
	
	<jstl:if test="${command == 'show' }">
		<acme:form-textbox code="auditor.audit-record.form.label.moreInfo" path="moreInformation" readonly="true"/>
		<acme:form-moment code="auditor.audit-record.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	

	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title" />
	<acme:form-textarea code="auditor.audit-record.form.label.body" path="body" />
	
	<jstl:if test="${command == 'update' }">
		<acme:form-checkbox code="auditor.audit-records.form.label.status" path="status"/>
	</jstl:if>
	
	
	<acme:form-submit test="${command == 'create'}"
		code="auditor.auditRecord.form.button.create"
		action="/auditor/audit-records/create?jobid=${param.jobid}"/>
		
		
	<acme:form-submit test="${command == 'update'}"
		code="auditor.auditRecord.form.button.update"
		action="/auditor/audit-records/update?jobid=${param.jobid}"/>
	
	<acme:form-return code="auditor.audit-records.form.button.return"/>
		
	
	
</acme:form>

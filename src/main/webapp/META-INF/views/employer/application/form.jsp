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
	<acme:form-textbox readonly="true" code="employer.application.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-moment readonly="true" code="employer.application.form.label.moment" path="moment"/>
	<acme:form-textbox readonly="true" code="employer.application.form.label.statement" path="statement"/>
	<acme:form-textarea readonly="true" code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea readonly="true" code="employer.application.form.label.qualifications" path="qualifications"/>
	<acme:form-select code="employer.application.form.label.acceptReject" path="acceptReject">
		<acme:form-option code="employer.application.form.label.accept" value="accept"/>
		<acme:form-option code="employer.application.form.label.reject" value="reject"/>
	</acme:form-select>
	<acme:form-textarea code="employer.application.form.label.justification" path="justification"/>
	
	<acme:form-submit test="${command == 'listMine'}" 
		code="employer.application.form.button.listMine" 
		action="/employer/application/listMine"/>
	<acme:form-submit test="${command == 'show'}"
		code="employer.application.form.button.update"
		action="/employer/application/update"/>
	<acme:form-submit test="${command == 'update'}"
		code="employer.application.form.button.update"
		action="/employer/application/update"/>
	
	<acme:form-return code="employer.application.form.button.return"/>
</acme:form>

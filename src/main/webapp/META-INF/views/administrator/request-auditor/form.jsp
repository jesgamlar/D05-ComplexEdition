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
	<acme:form-textbox code="administrator.requestAuditor.form.label.firm" path="firm"/>
	<acme:form-textbox code="administrator.requestAuditor.form.label.responsibilityStatement" path="responsibilityStatement"/>
	
	<acme:form-submit code="administrator.requestAuditor.form.button.delete" action="/administrator/request-auditor/delete"/>
	<acme:form-return code="administrator.requestAuditor.form.button.return"/>
</acme:form>

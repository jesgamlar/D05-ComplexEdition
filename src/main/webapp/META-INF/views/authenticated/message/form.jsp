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
	<acme:form-textbox code="authenticated.message.form.label.title" path="title"/>
	<acme:check-access test="${command != 'create' }">
		<acme:form-moment readonly="true" code="authenticated.message.form.label.moment" path="moment"/>
		<acme:form-textbox readonly="true" code="authenticated.message.form.label.messageThreadTitle" path="messageThreadTitle"/>
	</acme:check-access>
	<acme:form-textarea code="authenticated.message.form.label.tags" path="tags"/>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	<acme:check-access test="${command == 'create' }">
		<acme:form-checkbox code="authenticated.messages.form.label.checkbox" path="accept"/>
	</acme:check-access>
	
	<acme:form-return code="authenticated.message.form.button.return"/>
	
	<acme:form-submit test="${command == 'create'}"
		code="authenticated.message.form.button.create"
		action="/authenticated/message/create?${pageContext.request.queryString}"/>
		
</acme:form>

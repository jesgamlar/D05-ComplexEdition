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
	<acme:form-textbox code="provider.requestEntity.form.label.title" path="title"/>
	<acme:form-moment code="provider.requestEntity.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="provider.requestEntity.form.label.text" path="text"/>
	<acme:form-money code="provider.requestEntity.form.label.rewardMin" path="rewardMin"/>
	<acme:form-money code="provider.requestEntity.form.label.rewardMax" path="rewardMax"/>
	<acme:form-textbox code="provider.requestEntity.form.label.ticker" path="ticker" placeholder="RXXXX-99999"/>
	
	<acme:form-checkbox code="provider.requestEntity.form.label.checkbox"  path="accept"/>
	
	<acme:form-submit test="${command == 'create'}"

		code="provider.requestEntity.form.button.create"

		action="/provider/request-entity/create"/>
	
	
	<acme:form-return code="provider.requestEntity.form.button.return"/>
</acme:form>

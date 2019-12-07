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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.requestEntity.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.requestEntity.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.requestEntity.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.requestEntity.form.label.text" path="text"/>
	<acme:form-textbox code="authenticated.requestEntity.form.label.rewardMin" path="rewardMin"/>
	<acme:form-textbox code="authenticated.requestEntity.form.label.rewardMax" path="rewardMax"/>
	<acme:form-textbox code="authenticated.requestEntity.form.label.ticker" path="ticker"/>
	
	<acme:form-return code="authenticated.requestEntity.form.button.return"/>
</acme:form>

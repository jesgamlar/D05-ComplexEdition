<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.messageThread.list.label.title" path="title" width="60%"/>
	<acme:list-column code="authenticated.messageThread.list.label.moment" path="moment" width="40%"/>
</acme:list>

<acme:form>
	<acme:form-return code="authenticated.message.list.button.return"/>
	<acme:form-submit code="authenticated.message.list.button.create" action="/authenticated/message/create?${pageContext.request.queryString}" method="get"/>
</acme:form>
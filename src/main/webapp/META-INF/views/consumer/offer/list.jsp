<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="consumer.offer.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="consumer.offer.list.label.deadline" path="deadline" width="20%"/>
	<acme:list-column code="consumer.offer.list.label.title" path="title" width="40%"/>
	
</acme:list>

<acme:form>
    <acme:form-submit code="consumer.offer.form.button.create" action="/consumer/offer/create" method="get"/>
</acme:form>
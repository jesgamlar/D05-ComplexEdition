<%@ page language= "java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.muniz-bulletin.list.label.name" path="name" width="20%"/>
	<acme:list-column code="anonymous.muniz-bulletin.list.label.surname" path="surname" width="20%"/>
	<acme:list-column code="anonymous.muniz-bulletin.list.label.idCode" path="idCode" width="20%"/>
</acme:list>

<acme:form>
	<acme:form-submit code="anonymous.muniz-bulletin.list.button.advance" action="/anonymous/muniz-bulletin/create" method="get"/>
</acme:form>
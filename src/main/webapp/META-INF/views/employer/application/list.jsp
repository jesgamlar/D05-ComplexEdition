<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.application.list.label.referenceNumber" path="referenceNumber" width="20%"/>
	<acme:list-column code="employer.application.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="employer.application.list.label.title" path="statement" width="40%"/>
	
</acme:list>

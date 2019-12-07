<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.audit-record.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.audit-record.list.label.moreInfo" path="moreInformation" width="20%"/>
	<acme:list-column code="authenticated.audit-record.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="authenticated.audit-record.list.label.body" path="body" width="40%"/>
	
</acme:list>
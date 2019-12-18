<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.requestAuditor.list.label.username" path="userUsername" width="20%"/>
	<acme:list-column code="administrator.requestAuditor.list.label.firm" path="firm" width="40%"/>
	<acme:list-column code="administrator.requestAuditor.list.label.responsibilityStatement" path="responsibilityStatement" width="40%"/>

</acme:list>

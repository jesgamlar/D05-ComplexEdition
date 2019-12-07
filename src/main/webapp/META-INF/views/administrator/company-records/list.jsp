<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.company-records.list.label.name" path="incorporated" width="40%"/>
	<acme:list-column code="administrator.company-records.list.label.sector" path="sector" width="20%"/>
	<acme:list-column code="administrator.company-records.list.label.phone" path="phone" width="20%"/>
	<acme:list-column code="administrator.company-records.list.label.stars" path="stars" width="20%"/>
	
</acme:list>

<acme:form>
	<acme:form-submit code="administrator.company-records.list.button.create" action="/administrator/company-records/create" method="get"/>
</acme:form>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.investorRecord.list.label.name" path="name" width="20%"/>
	<acme:list-column code="administrator.investorRecord.list.label.sector" path="sector" width="35%"/>
	<acme:list-column code="administrator.investorRecord.list.label.investingStatement" path="investingStatement" width="35%"/>
	<acme:list-column code="administrator.investorRecord.list.label.stars" path="stars" width="10%" />
</acme:list>

<acme:form >
	<acme:form-submit method="get"
		code="administrator.investorRecord.form.button.create" 
		action="/administrator/investor-record/create"/>
</acme:form>
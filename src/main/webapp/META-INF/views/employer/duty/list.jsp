<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.duty.list.label.title" path="title" width="20%"/>
	<acme:list-column code="employer.duty.list.label.timexWeek" path="timexWeek" width="20%"/>
	
</acme:list>

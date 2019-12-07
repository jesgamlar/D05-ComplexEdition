<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.messageThread.list.label.title" path="title" width="50%"/>
	<acme:list-column code="authenticated.messageThread.list.label.moment" path="starterUsername" width="50%"/>
</acme:list>
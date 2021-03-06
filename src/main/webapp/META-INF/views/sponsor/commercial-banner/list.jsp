<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="sponsor.commercial-banner.list.label.picture" path="picture" width="20%"/>
	<acme:list-column code="sponsor.commercial-banner.list.label.url" path="url" width="40%"/>
</acme:list>

<acme:form>
	<acme:form-submit code="sponsor.commercial-banner.list.button.create" action="/sponsor/commercial-banner/create" method="get"/>
</acme:form>
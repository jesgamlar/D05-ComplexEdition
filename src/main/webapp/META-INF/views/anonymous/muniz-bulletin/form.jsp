<%@ page language= "java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.muniz-bulletin.form.label.name" path="name"/>
	<acme:form-textarea code="anonymous.muniz-bulletin.form.label.surname" path="surname"/>
	<acme:form-textarea code="anonymous.muniz-bulletin.form.label.idCode" path="idCode"/>
	
	<acme:form-submit code="anonymous.muniz-bulletin.form.button.create" action="/anonymous/muniz-bulletin/create"/>
	<acme:form-return code="anonymous.muniz-bulletin.form.button.return"/>
</acme:form>
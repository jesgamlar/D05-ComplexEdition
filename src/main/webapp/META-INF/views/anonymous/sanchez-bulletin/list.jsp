<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
    <acme:list-column code="anonymous.sanchez-bulletin.list.label.date" path="date" width="20%"/>
    <acme:list-column code="anonymous.sanchez-bulletin.list.label.author" path="author" width="20%"/>
    <acme:list-column code="anonymous.sanchez-bulletin.list.label.city" path="city" width="30%"/>
    <acme:list-column code="anonymous.sanchez-bulletin.list.label.country" path="country" width="30%"/>
</acme:list>

<acme:form>
    <acme:form-submit code="anonymous.sanchez-bulletin.form.button.create" action="/anonymous/sanchez-bulletin/create" method="get"/>
</acme:form>

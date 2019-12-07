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
    <acme:list-column code="anonymous.martin-bulletin.list.label.date" path="date" width="20%"/>
    <acme:list-column code="anonymous.martin-bulletin.list.label.name" path="name" width="20%"/>
    <acme:list-column code="anonymous.martin-bulletin.list.label.surname" path="surname" width="20%"/>
    <acme:list-column code="anonymous.martin-bulletin.list.label.age" path="age" width="5%"/>
    <acme:list-column code="anonymous.martin-bulletin.list.label.text" path="text" width="35%"/>
</acme:list>

<acme:form>
    <acme:form-submit code="anonymous.martin-bulletin.form.button.create" action="/anonymous/martin-bulletin/create" method="get"/>
</acme:form>

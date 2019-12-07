<%--
- form.jsp
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
    <acme:list-column code="anonymous.gamez-bulletin.list.label.food" path="food" width="20%"/>
    <acme:list-column code="anonymous.gamez-bulletin.list.label.kcalories" path="kcalories" width="15%"/>
    <acme:list-column code="anonymous.gamez-bulletin.list.label.moment" path="moment" width="15%"/>
    <acme:list-column code="anonymous.gamez-bulletin.list.label.directions" path="directions" width="50%"/>
</acme:list>

<acme:form>
    <acme:form-submit code="anonymous.gamez-bulletin.form.button.create" action="/anonymous/gamez-bulletin/create" method="get"/>
</acme:form>
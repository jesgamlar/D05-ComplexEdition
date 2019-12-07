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

<acme:form readonly="true">
	<acme:form-textbox code="administrator.list-data.form.label.nAnn" path="numberAnnouncements"/>
    <acme:form-textbox code="administrator.list-data.form.label.nCom" path="numberCompanies"/>
    <acme:form-textbox code="administrator.list-data.form.label.nInv" path="numberInvestors"/>
    <acme:form-textbox code="administrator.list-data.form.label.minOff" path="minRewardOffer"/>
    <acme:form-textbox code="administrator.list-data.form.label.maxOff" path="maxRewardOffer"/>
    <acme:form-textbox code="administrator.list-data.form.label.avgOff" path="averageRewardOffer"/>
    <acme:form-textbox code="administrator.list-data.form.label.derOff" path="derivationRewardOffer"/>
    <acme:form-textbox code="administrator.list-data.form.label.minReq" path="minRewardRequest"/>
    <acme:form-textbox code="administrator.list-data.form.label.maxReq" path="maxRewardRequest"/>
    <acme:form-textbox code="administrator.list-data.form.label.avgReq" path="averageRewardRequest"/>
    <acme:form-textbox code="administrator.list-data.form.label.derReq" path="derivationRewardRequest"/>
</acme:form>

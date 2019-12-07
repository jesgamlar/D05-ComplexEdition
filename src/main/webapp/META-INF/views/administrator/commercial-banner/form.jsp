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

<acme:form>
	<acme:form-url code="administrator.commercial-banner.form.label.picture" path="picture"/>
	<acme:form-url code="administrator.commercial-banner.form.label.url" path="url"/>
	<acme:form-textarea code="administrator.commercial-banner.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCardHolder" path="creditcardholder"/>
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCardBrand" path="creditcardbrand"/>
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCardDeadline" path="creditcarddeadline" placeholder="MM/YY"/>
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCardNumber" path="creditcardnumber"/>
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCardCVV" path="creditcardcvv"/>
	
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.commercial-banner.form.button.update"
		action="/administrator/commercial-banner/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.commercial-banner.form.button.delete"
		action="/administrator/commercial-banner/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.commercial-banner.form.button.create"
		action="/administrator/commercial-banner/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.commercial-banner.form.button.update"
		action="/administrator/commercial-banner/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.commercial-banner.form.button.delete"
		action="/administrator/commercial-banner/delete"/>
	<acme:form-return code="administrator.commercial-banner.form.button.return"/>
</acme:form>

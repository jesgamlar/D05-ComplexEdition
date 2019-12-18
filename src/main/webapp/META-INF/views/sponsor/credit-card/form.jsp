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
	<acme:form-textbox code="sponsor.creditCard.form.label.creditCard.holder" path="holder"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.creditCard.brand" path="brand"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.creditCard.deadline" path="deadline" placeholder="MM/YY"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.creditCard.number" path="number" placeholder="5[1-5][0-9]{14}$"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.creditCard.cvv" path="cvv" placeholder="^\\d{3,4}$"/>
	
	<acme:form-submit test="${command == 'create'}" code="sponsor.creditCard.form.button.create" action="/sponsor/credit-card/create"/>
	<acme:form-submit test="${command == 'update'}" code="sponsor.creditCard.form.button.update" action="/sponsor/credit-card/update"/>
	<acme:form-return code="sponsor.creditCard.form.button.return"/>
</acme:form>

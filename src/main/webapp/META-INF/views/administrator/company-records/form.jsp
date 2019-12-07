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
	<acme:form-textbox code="administrator.company-records.form.label.name" path="name"/>
	<acme:form-select code="administrator.company-records.form.label.isIncorporated" path="isIncorporated">
		<acme:form-option code="form.option.true" value="true"/>
		<acme:form-option code="form.option.false" value="false"/>
	</acme:form-select>
	<acme:form-textbox code="administrator.company-records.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.company-records.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="administrator.company-records.form.label.activities" path="activities"/>
	<acme:form-url code="administrator.company-records.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.company-records.form.label.phone" path="phone"/>
	<acme:form-textbox code="administrator.company-records.form.label.email" path="email" placeholder="email@address.com"/>
	<acme:form-integer code="administrator.company-records.form.label.stars" path="stars" placeholder="0-5"/>
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.company-records.form.button.update"
		action="/administrator/company-records/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.company-records.form.button.delete"
		action="/administrator/company-records/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.company-records.form.button.create"
		action="/administrator/company-records/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.company-records.form.button.update"
		action="/administrator/company-records/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.company-records.form.button.delete"
		action="/administrator/company-records/delete"/>
	
	<acme:form-return code="administrator.company-records.form.button.return"/>
</acme:form>

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
	<acme:form-textbox code="administrator.investorRecord.form.label.name" path="name"/>
	<acme:form-textarea code="administrator.investorRecord.form.label.sector" path="sector"/>
	<acme:form-textarea code="administrator.investorRecord.form.label.investingStatement" path="investingStatement"/>
	<acme:form-integer code="administrator.investorRecord.form.label.stars" path="stars" placeholder="0-5"/>
			
	<acme:form-submit test="${command == 'show'}"
		code="administrator.investorRecord.form.button.update" 
		action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.investorRecord.form.button.delete" 
		action="/administrator/investor-record/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.investorRecord.form.button.create" 
		action="/administrator/investor-record/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.investorRecord.form.button.update" 
		action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.investorRecord.form.button.update" 
		action="/administrator/investor-record/update"/>
	<acme:form-return
		code="administrator.investorRecord.form.button.return"/>
</acme:form>

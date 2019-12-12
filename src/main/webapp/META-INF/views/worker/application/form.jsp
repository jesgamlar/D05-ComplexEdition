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
	
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" readonly="true" />
		<acme:form-textbox code="worker.application.form.label.jobTitle" path="jobTitle" readonly="true"/>
		<acme:form-moment code="worker.application.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>

	<acme:form-textbox code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
	
	<p id="submit"></p>
	
	<acme:form-return code="worker.application.form.button.return"/>
	
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			var jobid = (window.location.href).split("?jobid=")[1];
			
			var submit = `<acme:form-submit test="${command == 'create'}"
				code='worker.application.form.button.create'
				action='/worker/application/create?jobid=`+jobid+`' />`;
				
			document.getElementById("submit").innerHTML = submit;
			
		});
	</script>
	
	
</acme:form>

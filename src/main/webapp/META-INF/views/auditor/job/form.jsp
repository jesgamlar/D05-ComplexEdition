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
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference"/>

	<acme:form-checkbox code="auditor.job.form.label.status" path="finalMode"/>

	<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary"/>
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="auditor.job.form.label.description" path="description"/>
	

	<p id="duties"></p>
	 <p id="auditRecords"></p>
	
	<acme:form-return code="auditor.job.form.button.return"/>
	
	<script type="text/javascript">
	
        $(document).ready(function() {
            var submit = `<acme:form-submit code='auditor.job.form.button.listDuties' method='get' action='/authenticated/duty/list?id=${id}' />`;
            document.getElementById("duties").innerHTML = submit;
        });
    </script>
    
   
	
	<script type="text/javascript">
        $(document).ready(function() {
            var submit = `<acme:form-submit code='auditor.job.form.button.listAuditRecords' method='get' action='/authenticated/audit-records/list-mine?id=${id}' />`;
            document.getElementById("auditRecords").innerHTML = submit;
        });
    </script>

	
</acme:form>



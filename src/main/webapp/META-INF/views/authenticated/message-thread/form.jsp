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
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.messageThread.form.label.starterUsername" path="starterUsername"/>


	<p id="messages"></p>
	
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var submit = `<acme:form-submit code='authenticated.messageThread.form.button.listMessages' method='get' action='/authenticated/message/list-mine?id=${id}' />`;
			document.getElementById("messages").innerHTML = submit;
		});
	</script>
</acme:form>
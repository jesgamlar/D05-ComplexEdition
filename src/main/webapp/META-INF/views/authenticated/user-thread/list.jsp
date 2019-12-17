<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.userThread.list.label.title" path="userUsername" width="50%"/>
</acme:list>

<acme:form>
	<p id="submit"></p>
</acme:form>



		<script type="text/javascript">
		$(document).ready(function() {
			
			var mtid = (window.location.href).split("?id=")[1];
			
			var submit = `<acme:form-submit test="${command == 'create'}"
				code='authenticated.user-thread.form.button.create'
				action='/worker/application/create?mtid=`+mtid+`' />`;
				
			document.getElementById("submit").innerHTML = submit;
			
		});
	</script>
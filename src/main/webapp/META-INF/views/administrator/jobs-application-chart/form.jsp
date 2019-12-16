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

<h2>
	<acme:message code="administrator.charts.form.title"/>
</h2>

<div>
	<canvas id="canvasJobs"></canvas>
	<canvas id="canvasApplications"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		var dataJobs = {
				labels: [ "Draft", "Published"
					
				],
				datasets: [
					{	label:"Jobs",
						data : [
							<jstl:forEach var="label" items="${jobsStatus.keySet()}">
								"<jstl:out value="${jobsStatus.get(label)}"/>",
							</jstl:forEach>
						]
					}
				]
		};
		
		var dataApplications = {
				labels: [ "Accepted", "Pending", "Rejected"
					
				],
				datasets: [
					{	label:"Applications",
						data : [
							<jstl:forEach var="label" items="${applicationStatus.keySet()}">
								"<jstl:out value="${applicationStatus.get(label)}"/>",
							</jstl:forEach>
						]
					}
				]
		};
			
		var options = {
				scales : {
					yAxes : [
						{
							ticks: {
								suggestedMin : 0.0,
								suggestedMax : 7.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};
	
		var canvasJobs, contextJobs, canvasApplications, contextApplications;
	
		canvasJobs = document.getElementById("canvasJobs");
		contextJobs = canvasJobs.getContext("2d");
		new Chart(contextJobs, {
			type : "bar",
			data : dataJobs,
			options : options
		});
		
		canvasApplications = document.getElementById("canvasApplications");
		contextApplications = canvasApplications.getContext("2d");
		new Chart(contextApplications, {
			type : "bar",
			data : dataApplications,
			options : options
		});
	});
</script>
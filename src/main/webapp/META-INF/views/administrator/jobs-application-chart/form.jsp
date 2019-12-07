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
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		var data = {
				labels: [ "Draft", "Published"
					
					
				
					
					
				],
				datasets: [
					{	label:"Jobs",
						data : [
							<jstl:forEach var="label" items="${jobsStatus.keySet()}">
								"<jstl:out value="${jobsStatus.get(label)}"/>",
							</jstl:forEach>
						]
					},{	label:"Applications",
						data : [
							<jstl:forEach var="label" items="${jobsStatus.keySet()}">
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
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
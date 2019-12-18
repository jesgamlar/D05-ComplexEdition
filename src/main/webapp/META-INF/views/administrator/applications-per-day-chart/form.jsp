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
	<acme:message code="administrator.charts.form.applications"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		var data = {
				labels: [ <jstl:forEach var="label" items="${applicationsDates}">
							"<jstl:out value="${label.toString().split(' ')[0]}"/>",
						  </jstl:forEach>
					
				],
				datasets: [
					{	label:"<acme:message code="administrator.charts.form.applications.pending"/>",
						data : [
							<jstl:forEach var="label" items="${applicationsDates}">

								<jstl:out value="{x: '${label.toString().split(' ')[0]}', y: ${pendingApplications.get(label)}}" escapeXml="false"/>,

							</jstl:forEach>
						],
						borderColor: [
					          "#f38b4a"
					        ]
					},{	label:"<acme:message code="administrator.charts.form.applications.accepted"/>",
						data : [
							<jstl:forEach var="label" items="${applicationsDates}">

								<jstl:out value="{x: '${label.toString().split(' ')[0]}', y: ${acceptedApplications.get(label)}}" escapeXml="false"/>,
								//"<jstl:out value="${acceptedApplications.get(label)}"/>",

							</jstl:forEach>
						],
						borderColor: [
					          "#56d798"
					        ]
					},{	label:"<acme:message code="administrator.charts.form.applications.rejected"/>",
						data : [
							<jstl:forEach var="label" items="${applicationsDates}">
							<jstl:out value="{x: '${label.toString().split(' ')[0]}', y: ${rejectedApplications.get(label)}}" escapeXml="false"/>,
								//"<jstl:out value="${rejectedApplications.get(label)}"/>",

							</jstl:forEach>
						],
						borderColor: [
					          "#ff8397"
					        ]
					}
				]
		};
		
			
		var options = {
				scales : {

					xAxes:[{
						type:"time",
						distribution: "series",
					}],

					yAxes : [
						{
							ticks: {
								suggestedMin : 0.0,
								suggestedMax : 5.0
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
			type : "line",
			data : data,
			options : options
		});
	});
</script>
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
				labels: [
					<jstl:forEach var="label" items="${companiesSectors.keySet()}">
						"<jstl:out value="${label}"/>",
					</jstl:forEach>
				],
				datasets: [
					{	label:"Companies",
						data : [
							<jstl:forEach var="label" items="${companiesSectors.keySet()}">
								"<jstl:out value="${companiesSectors.get(label)}"/>",
							</jstl:forEach>
						]
					},{	label:"Investors",
						data : [
							<jstl:forEach var="label" items="${companiesSectors.keySet()}">
								"<jstl:out value="${investorsSectors.get(label)}"/>",
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
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>JMS&Email</title>
	<script>
		$(document).ready(function() {
			$("#jms-tab").addClass("active");
		});
	</script>	
</head>
<body>
		<h1>JMS&Email</h1>
		<h5><a href="javascript:history.back()"> >>Back</a></h5>
		<div>
			<br>
			<p style='text-align:center'>Email has been sent successfully.
			<br>(From:${mailMsg.from} --> To:${mailMsg.to[0]})</p>
		</div>
</body>
</html>
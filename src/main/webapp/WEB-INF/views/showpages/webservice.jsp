<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>WebService</title>

	<style type="text/css">
	</style>
	<script>
		$(document).ready(function() {
			$("#webservice-tab").addClass("active");
		});
	</script>	
</head>
<body>
		<h1>Web Tools</h1>
		<h5>1.SOAP Web Service Based on CXF </h5>
		<div>
			&nbsp;&nbsp;&nbsp;<a href="${ctx}/cxf/userCXFService">get wsdl file.</a>
		</div>
</body>
</html>
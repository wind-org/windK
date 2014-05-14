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
		<h5>1.SOAP Web Service by CXF </h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/cxf/soap/userSoapService"> get user wsdl file.</a>
		</div>
		<h5>2.Jaxrs Restful Web Service by CXF </h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/cxf/user/1.xml"> get user xml file.</a>
		</div>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/cxf/user/1.json"> get user json file.</a>
		</div>
</body>
</html>
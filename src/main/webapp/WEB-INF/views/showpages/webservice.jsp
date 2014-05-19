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
		<h5>1.SOAP Web Service Implemented by CXF </h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/cxf/soap/userSoapService?wsdl" target="_blank"> get user wsdl file.</a>
		</div>
		<h5>2.Jaxrs Restful Web Service Implemented by CXF </h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/cxf/jaxrs/user/1.xml" target="_blank"> get user xml info.</a>
		</div>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/cxf/jaxrs/user/1.json" target="_blank"> get user json info.</a>
		</div>
		<h5>3.Restful Service Implemented by SpringMVC </h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/rest/user/1.xml" target="_blank"> get user xml info.</a>
		</div>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp;<a href="${ctx}/rest/user/1.json" target="_blank"> get user json info.</a>
		</div>
		<h5>4.SOAP Client Implemented by CXF</h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp; The method 'testCXFSoapService' of 'WebServiceTest' in function test shows how to use cxf client.
		</div>
		<h5>5.Restful Client Implemented by Spring RestTemplate</h5>
		<div>
			&nbsp;&nbsp;&nbsp;>>&nbsp; The other methods of 'WebServiceTest' in function test show how to use spring rest client.<br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ps:This case is just a lead to show 'Get' request, please refer to official document to built 'Post','Put','Delet' request.
		</div>
</body>
</html>
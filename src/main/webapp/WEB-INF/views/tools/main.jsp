<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Web Tools</title>
	<script>
		$(document).ready(function() {
			$("#web-tab").addClass("active");
		});
	</script>	
</head>
<body>
		<h1>Web Tools</h1>
		<h2>Static Content Download</h2>
		<p>
			<a href="${ctx}/staticContent?contentPath="></a>
		</p>
		<h2></h2>
</body>
</html>
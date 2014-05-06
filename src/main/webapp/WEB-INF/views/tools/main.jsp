<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Web Tools</title>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<script>
		$(document).ready(function() {
			$("#web-tab").addClass("active");
		});
	</script>	
</head>
<body>
		<h1>Web Tools</h1>
		<h5>1.Static Content Download</h5>
		<div>
			&nbsp;&nbsp;&nbsp;<a href="${ctx}/tools/staticContent?contentPath=static/images/eye.jpg">Click and get an image from server !</a>
		</div>
		<h5>2.Remote Content Download</h5>
		<div>
			<div>
				<form action="${ctx}/tools/remoteContent" method="post" name="urlForm" class="form-horizontal" id="urlForm">
				<span><input type="radio" name="type" value="Y" checked="checked">By Apache Http Client &nbsp; &nbsp;<input type="radio" name="type" value="N">By JDK UrlConnection</span>
				<span>URL:<input type="text" name="remoteUrl" id="remoteUrl" class="input-xxlarge required"><input type="submit" class="btn" id="sub_btn" value="submit" onclick=""/></span>
				</form>
			</div>
		</div>
</body>
</html>
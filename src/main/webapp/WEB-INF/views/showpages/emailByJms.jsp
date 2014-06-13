<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>JMS&Email</title>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_en.js" type="text/javascript"></script>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<script>
		$(document).ready(function() {
			$("#jms-tab").addClass("active");
			$("#emailForm").validate();
		});
	</script>	
</head>
<body>
		<h1>JMS&Email</h1>
		<h5>1.Email Tool Using JMS </h5>
		<div >
			<form action="${ctx}/jms/sendMsg" method="post" name="emailForm" class="form-horizontal" id="emailForm">
				<label>Subject:</label><input type="text" id="mail_subject" name="subject" class="input-xxlarge required"/><br>
				<label>To:</label><input type="text" id="mail_to" name="to" class="input-xlarge required" value="ncsnail@163.com"/><br>
				<label>From:</label><input type="text" id="mail_from" name="from" class="input-xlarge required" value="wind.k.org@gmail.com" readonly="readonly"/><br>
				<label>Content:</label><textarea id="mail_text" name="text" class="input-xxlarge required"></textarea> <br>
				<p>&nbsp;&nbsp;</p>
				<input type="submit" class="btn" id="sub_btn" value="submit" /> &nbsp;&nbsp;<input type="reset" class="btn" id="res_btn" value="reset" />
			</form>
		</div>

</body>
</html>
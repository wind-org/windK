<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="span2">
	<h1>Part I</h1>
	<div class="submenu">
		<a id="account-tab"href="${ctx}/user">User Managerment</a>
	</div>
	<h1>Part II</h1>
	<div class="submenu">
		<a id="web-tab" href="${ctx}/tools">Web Tools</a>
		<a id="webservice-tab"href="${ctx}/webservice">WebService</a>
		<a id="jms-tab" href="${ctx}/jms">JMS&Email</a>
		<a id="monitor-tab" href="${ctx}/story/monitor">Performance Monitoring</a>
	</div>
	<h1>Part III</h1>
	<div class="submenu">
		<a id="cache-tab" href="${ctx}/story/cache">Cache</a>
		<a id="security-tab" href="${ctx}/story/security">Security</a>
		<a id="schedule-tab" href="${ctx}/story/schedule">Schedule</a>
		<a id="persistence-tab" href="${ctx}/story/persistence">Persistence</a>
		<a id="reids-tab" href="${ctx}/story/redis">Redis</a>
	</div>
</div>
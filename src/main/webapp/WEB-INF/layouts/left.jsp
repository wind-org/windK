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
		<a id="webservice-tab"href="${ctx}/story/webservice">WebService</a>
		<a id="hystrix-tab" href="${ctx}/hystrix">Hystrix</a>
		<a id="jmx-tab" href="${ctx}/story/jmx">JMX</a>
		<a id="log-tab" href="${ctx}/story/log">Log</a>
		<a id="monitor-tab" href="${ctx}/story/monitor">性能监控演示</a>
	</div>
	<h1>Part III</h1>
	<div class="submenu">
		<a id="persistence-tab" href="${ctx}/story/persistence">持久化高级演示</a>
		<a id="jms-tab" href="${ctx}/story/jms">JMS演示</a>
		<a id="reids-tab" href="${ctx}/story/redis">Redis演示</a>
		<a id="cache-tab" href="${ctx}/story/cache">Cache演示</a>
		<a id="schedule-tab" href="${ctx}/story/schedule">定时任务演示</a>
		<a id="security-tab" href="${ctx}/story/security">安全高级演示</a>
		<a id="utilizes-tab"href="${ctx}/story/utilizes">工具类演示</a>
		<a id="executablewar-tab"href="${ctx}/story/executablewar">可运行war包演示</a>
	</div>
</div>
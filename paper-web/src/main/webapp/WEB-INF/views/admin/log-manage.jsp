<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>日志</h4>

<div id="list-data"></div>

<script id="list-template" type="text/html">
<table id="user-table" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>编号</th>
			<th width="10%;">类型</th>
			<th width="20%;">创建人</th>
			<th width="20%;">创建时间</th>
			<th width="20%;">操作IP</th>
			<th width="10%;">操作URI</th>
			<th width="10%;">操作方式</th>
            <th width="10%;">提交数据</th>
			<th width="10%;">操作代理</th>
			<th width="10%;">异常信息</th>
		</tr>
	</thead>
	<tbody>
    {{each result as value i}}
		<tr>
			<td>{{ value.id }}</td>
			<td>{{ value.type }}</td>
			<td>{{if value.createBy != null }}{{ value.createBy.name }}{{/if}}</td>
			<td>{{ value.createDate }}</td>
			<td>{{ value.remoteAddr }}</td>
			<td>{{ value.requestUri }}</td>
			<td>{{ value.method }}</td>
			<td>{{ value.params }}</td>
			<td>{{ value.userAgent }}</td>
			<td>{{ value.exception }}</td>
		</tr>
    {{/each}}
	</tbody>
</table>
<div>
    {{#pagination }}
</div>
</script>


<script type="text/javascript">
<c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/log-manage-page.js']);
</c:if>
<c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/log-manage-page.js']);
</c:if>
</script>

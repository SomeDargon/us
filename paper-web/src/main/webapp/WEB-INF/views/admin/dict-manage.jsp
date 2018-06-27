<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>标签管理<a class="btn-link btn-sm" href="${ctx }/admin/dict/form" class="btn-link"><span class="glyphicon glyphicon-plus-sign"></span>新标签</a></h4>
<form class="form-inline well" role="form" id="search-form">
	<div class="form-group">
		<label for="type">类型 :</label>
		<select class="form-control input-sm selectpicker" multiple data-width="300px" data-style="btn-sm" data-live-search="true" data-size=10 name="type" id="type" >
			<option value="">全部</option>
			<c:forEach items="${dictTypes }" var="dictType">
				<option value="${dictType }">${dictType }</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="description">描述:</label>
		<input class="form-control input-sm" name="description" id="description" size="30" type="text">
	</div>
	<button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
	<a href="${ctx }/admin/dict/manage" class="btn btn-default btn-sm">刷新</a>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">
	<table id="dict-table" class="table table-bordered table-condensed table-hover">
		<thead>
		<tr>
			<th><input type="checkbox" name="post[]" value="24"></th>
			<th>标签名</th>
			<th>数据值</th>
			<th>类型</th>
			<th>排序</th>
			<th>描述</th>
			<th>备注</th>
			<shiro:hasPermission name="sys:menu:edit"><th>操作</th></shiro:hasPermission>
		</tr>
		</thead>
		<tbody>
		{{each result as value i}}
		<tr>
			<td><input type="checkbox" name="post[]" value="24"></td>
			<td>{{ value.label }}</td>
			<td>{{ value.value }}</td>
			<td>{{ value.type }}</td>
			<td>{{ value.sort }}</td>
			<td>{{ value.description }}</td>
			<td>{{ value.remarks }}</td>
			<shiro:hasPermission name="sys:menu:edit">
				<td>
					<a class="btn-link" href="${ctx}/admin/dict/form?id={{ value.id }}"><span class="glyphicon glyphicon-edit"></span>修改</a>
					<button type="button" class="btn-link dict-del" data='{"id":"{{ value.id }}"}'><span class="glyphicon glyphicon-trash"></span>删除</button>
				</td>
			</shiro:hasPermission>
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
	requirejs(['${ctx}/assets/js/app/admin/dict-manage-page.js']);
	</c:if>
	<c:if test="${env != 'pro' }">
	requirejs(['${ctx}/assets/js/app/admin/dict-manage-page.js']);
	</c:if>
</script>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h4>日程管理</h4>
<form class="form-inline well" role="form" id="search-form">
    <div class="form-group">
        <label for="startTime" class="col-md-4 control-label">时间</label>
        <div  class="input-group date form_datetime col-md-8" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
            <input id="startTime" name="startTime" class="form-control" size="20" type="text" value="" readonly/>
            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        </div>
    </div>
    <button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
    <a class="btn-link btn-sm" href="${ctx }/admin/schedule/form" class="btn-link"><span class="glyphicon glyphicon-plus-sign"></span>
        添加日程</a>
    <a href="${ctx }/admin/schedule/manage" class="btn btn-default btn-sm">刷新</a>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">

    <table id="dict-table" class="table table-bordered table-condensed table-hover">
        <thead>
        <tr>
            <th style="width:15%;">开始时间</th>
            <th style="width:20%;">结束时间</th>
            <th style="width:10%;">是否通知</th>
            <th style="width:10%;">地点</th>
            <th style="width:10%;">描述</th>
            <th style="width:10%;">日程类型</th>
            <th style="width:10%;">操作人员</th>
            <shiro:hasPermission name="sys:menu:edit"><th>操作</th></shiro:hasPermission>
        </tr>
        </thead>
        <tbody>
        {{each result as value i}}
        <tr>
            <td>{{ value.startTime }}</td>
            <td>{{ value.endTime }}</td>
            <td>{{ value.isMessage }}</td>
            <td>{{ value.site }}</td>
            <td>{{ value.remarks }}</td>
            <td>
                <c:forEach items="${fns:getDictList('schedule_type')}" var="dict">
                    {{if value.type == '${dict.value}'}}${dict.label}{{/if}}
                </c:forEach>
            </td>
            <td>
                {{if value.user}}
                    {{value.user.name}}
                {{/if}}
            </td>
            <shiro:hasPermission name="sys:menu:edit">
                <td><a class="btn-link" href="${ctx}/admin/schedule/form?id={{ value.id }}">修改</a>

                    <button type="button" class="btn-link schedule-del" data='{"id":"{{ value.id }}"}'>删除</button>
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
    requirejs(['${ctx}/assets/js/app/admin/schedule-manage-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/schedule-manage-page.js']);
    </c:if>
</script>

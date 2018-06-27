<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h4>我的审批<a href="${ctx }/admin/task/form" class="btn-link btn-sm pull-right"><span class="glyphicon glyphicon-plus-sign"></span>添加审批</a></h4>
<form class="form-inline well" role="form" id="search-form">
    <div class="form-group">
        <label for="insertTime" class="col-md-4 control-label">时间</label>
        <div  class="input-group date form_datetime col-md-8" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
            <input id="insertTime"  name="insertTime" class="form-control" size="20" type="text" value="" readonly>
            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        </div>
        <input type="hidden" id="dtp_input1" value="" /><br/>
    </div>
    <button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
    <a href="${ctx }/admin/task/manage" class="btn btn-default btn-sm">刷新</a>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">
    <table id="dict-table" class="table table-bordered table-condensed table-hover">
        <thead>
        <tr>
            <th style="width:15%;">发起人</th>
            <th style="width:20%;">申请时间</th>
            <th style="width:10%;">处理人</th>
            <th style="width:10%;">当前环节</th>
            <th style="width:10%;">当前状态</th>
            <th style="width:10%;">工单编号</th>
            <shiro:hasPermission name="sys:menu:edit"><th>操作</th></shiro:hasPermission>
        </tr>
        </thead>
        <tbody>
        {{each result as value i}}
        <tr>
            <td>{{ value.initiator.name }}</td>
            <td>{{ value.insertTime}}</td>
            <td>{{ value.nextPeople.name }}</td>
            <td>
                <c:forEach items="${fns:getDictList('flow_status')}" var="dict">
                    {{if value.status == '${dict.value}'}}${dict.label}{{/if}}
                </c:forEach>
            </td>
            <td>
                <c:forEach items="${fns:getDictList('flow_type')}" var="dict">
                    {{if value.taskStatus == '${dict.value}'}}${dict.label}{{/if}}
                </c:forEach>
            </td>
            <td>
                {{value.id}}
            </td>
            <shiro:hasPermission name="sys:menu:edit">
                <td>
                    <a class="btn-link" href="${ctx}/admin/task/process/deal?id={{ value.id }}">审核</a>
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
    requirejs(['${ctx}/assets/js/app/admin/task-return-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/task-return-page.js']);
    </c:if>
</script>

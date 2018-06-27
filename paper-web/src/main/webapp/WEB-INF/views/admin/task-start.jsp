<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>添加审批</h4>
<div class="error alert alert-danger" style="display:none;">
</div>
<form id="task-form" class="form-horizontal" style="padding: 15px;">
    <div class="form-group">
        <label for="proType" class="col-sm-2 control-label">日程类型</label>
        <div class="col-sm-3">
            <c:forEach items="${fns:getDictList('task_type')}" var="dict">
                <input type="radio" id="radio${dict.id }"  id="proType" name="proType"  title="状态" value="${dict.value}"> ${dict.label }
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label for="x1"  class="col-sm-2 control-label">描述</label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-sm" id="x1" name="x1">
        </div>
    </div>
</form>

<div >

		<button id="task-submit" class="btn btn-primary btn-sm" type="button">保 存</button>

		<button id="task-cancel" class="btn btn-sm" type="button" onclick="history.go(-1)">返 回</button>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/task-start-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/task-start-page.js']);
    </c:if>
</script>
<script src="${ctx}/assets/js/lib/jquery/jquery-1.11.1.min.js"></script>
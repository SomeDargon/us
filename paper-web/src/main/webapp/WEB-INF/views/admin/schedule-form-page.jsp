<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>${empty schedule? "添加日程":"日程管理"  }</h4>
<div class="error alert alert-danger" style="display:none;">
</div>
<form id="schedule-form" class="form-horizontal" style="padding: 15px;">
	<input type="hidden" id="id" name="id" value="${schedule.id }"/>
    <div class="form-group">
        <label for="startTime" class="col-sm-2 control-label">开始时间</label>
        <div class="col-sm-3">
            <div  class="input-group date form_datetime col-md-10" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
                <input id="startTime" name="startTime" class="form-control" size="20" type="text"  value="<fmt:formatDate value="${schedule.startTime}" pattern="yyyy-MM-dd" /> " readonly/>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="endTime" class="col-sm-2 control-label">结束时间</label>
        <div class="col-sm-3">
            <div  class="input-group date form_datetime col-md-10" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
                <input id="endTime" name="endTime" class="form-control" size="20" type="text"  value="<fmt:formatDate value="${schedule.endTime}" pattern="yyyy-MM-dd" /> " readonly/>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">是否通知</label>
        <div class="col-sm-3">
            <c:forEach items="${fns:getDictList('yes_no')}" var="dict">
                <input type="radio" id="radio${dict.id }"  name="isMessage" ${schedule.isMessage==dict.value?"checked":""} value="${dict.value }" title="状态"> ${dict.label }
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">日程类型</label>
        <div class="col-sm-3">
            <c:forEach items="${fns:getDictList('schedule_type')}" var="dict">
                <input type="radio" id="radio${dict.id }"  id="type" name="type" ${schedule.type==dict.value?"checked":""} value="${dict.value }" title="状态"> ${dict.label }
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label for="site"  class="col-sm-2 control-label">地点</label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-sm" id="site" name="site" value="${schedule.site }">
        </div>
    </div>
	<div class="form-group">
		<label for="remarks" class="col-sm-2 control-label">描述</label>
		<div class="col-sm-3">
			<input type="text" class="form-control input-sm" id="remarks" name="remarks" value="${schedule.remarks }">
		</div>
	</div>
</form>

<div >

		<button id="schedule-submit" class="btn btn-primary btn-sm" type="button">保 存</button>

		<button id="schedule-cancel" class="btn btn-sm" type="button" onclick="history.go(-1)">返 回</button>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/schedule-form-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/schedule-form-page.js']);
    </c:if>
</script>
<script src="${ctx}/assets/js/lib/jquery/jquery-1.11.1.min.js"></script>
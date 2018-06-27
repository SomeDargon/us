<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>${empty replation.id? "添加用户":"修改用户"  }</h4>
<div class="error alert alert-danger" style="display:none;">
</div>
<form id="relation-form" class="form-horizontal" style="padding: 15px;">
	<input type="hidden" id="id" name="id" value="${relation.id }"/>
	<div class="form-group">
        <label for="uname" class="col-sm-2 control-label"><span class="text-fill">*</span>用户名</label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-sm required" id="uname" name="uname" value="${relation.uname}">
        </div>
	</div>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label"><span class="text-fill">*</span>姓名</label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-sm" id="name" name="name" value="${relation.name }">
        </div>
    </div>
    <div class="form-group">
        <label for="sex" class="col-sm-2 control-label"><span class="text-fill">*</span>性别</label>
        <div class="col-sm-3">
            <c:forEach items="${fns:getDictList('user_gender')}" var="dict">
                <input type="radio" id="radio${dict.id }" id="sex" name="sex" ${relation.sex==dict.value?"checked":""} value="${dict.value }" title="状态"> ${dict.label }
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label"><span class="text-fill">*</span>邮箱</label>
        <div class="col-sm-3">
            <input type="email" class="form-control input-sm" id="email" name="email" value="${relation.email }" title="邮箱必须填写">
        </div>
    </div>
    <div class="form-group">
        <label for="qq" class="col-sm-2 control-label">QQ号</label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-sm" id="qq" name="qq" value="${relation.qq }">
        </div>
    </div>
	<div class="form-group">
		<label for="iphone" class="col-sm-2 control-label"><span class="text-fill">*</span>手机号</label>
		<div class="col-sm-3">
			<input type="text" class="form-control input-sm" id="iphone" name="iphone" value="${relation.iphone }">
		</div>
	</div>
</form>

<div >

		<button id="relation-submit" class="btn btn-primary btn-sm" type="button">保 存</button>

		<button id="relation-cancel" class="btn btn-sm" type="button" onclick="history.go(-1)">返 回</button>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/relation-form-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/relation-form-page.js']);
    </c:if>
</script>
<script src="${ctx}/assets/js/lib/jquery/jquery-1.11.1.min.js"></script>
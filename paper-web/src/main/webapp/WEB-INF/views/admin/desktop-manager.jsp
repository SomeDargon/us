<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>个人桌面</h4>
<div class="col-lg-3">
	<div class="widget style1 lazur-bg">
		<a href="${ctx }/admin/personal/manage"  style="color: #FFFFFF">
			<div class="row">
				<div class="col-xs-4">
					<i class="fa fa-smile-o fa-5x"></i>
				</div>
				<div class="col-xs-8 text-right">
					<span></span>
					<h2 class="font-bold">人脉管理</h2>
				</div>
			</div>
		</a>
	</div>
</div>
<div class="col-lg-3">
	<div class="widget style1 yellow-bg">
		<a href="${ctx }/admin/schedule/manage" style="color: #FFFFFF">
			<div class="row">
				<div class="col-xs-4">
					<i class="fa fa-table fa-5x"></i>
				</div>
				<div class="col-xs-8 text-right">
					<span></span>
					<h2 class="font-bold">日程管理</h2>
				</div>
			</div>
		</a>
	</div>
</div>
<div class="col-lg-3">
	<div class="widget style1 navy-bg">
		<a class="" href="${ctx }/admin/task/return" style="color: #FFFFFF">
			<div class="row">
				<div class="col-xs-4">
					<i class="fa fa-folder-open-o fa-5x"></i>
				</div>
				<div class="col-xs-8 text-right">
					<span></span>
					<h2 class="font-bold">待审批</h2>
				</div>
			</div>
		</a>
	</div>
</div>
<div class="col-lg-3">
	<div class="widget style1 purple-bg">
		<a href="${ctx }/admin/user/manage" style="color: #FFFFFF">
			<div class="row">
				<div class="col-xs-4">
					<i class="fa fa-gear fa-5x"></i>
				</div>
				<div class="col-xs-8 text-right">
					<span></span>
					<h2 class="font-bold">系統管理</h2>
				</div>
			</div>
		</a>
	</div>
</div>
<div class="col-lg-3">
	<div class="widget style1 red-bg">
		<a href="${ctx }/admin/advertise/manage" style="color: #FFFFFF">
			<div class="row">
				<div class="col-xs-4">
					<i class="fa fa-gear fa-5x"></i>
				</div>
				<div class="col-xs-8 text-right">
					<span></span>
					<h2 class="font-bold">订单管理</h2>
				</div>
			</div>
		</a>
	</div>
</div>
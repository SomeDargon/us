<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<input type="hidden" id="url" value="${ctx}">
<div class="sidebar-scroll" style="overflow: hidden; outline: none;">
    <div class="nav-collapse" id="sidebar" style="height: 0px;">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="sidebar-menu">
            <li class="sub-menu" id="admin-post">
                    <a class="" href="${ctx }/admin/desktop">
                        <i class="icon-user"></i>
                        <span id="desktop">个人桌面</span>
                    </a>
            </li>
            <li class="sub-menu" id="admin-people">
                <a class="" href="javascript:;">
                    <i class="icon-dashboard"></i>
                    <span>人脉管理</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li id="admin-people-manage"><a href="${ctx }/admin/personal/manage" >人脉资源</a></li>
                    <li id="admin-see-manage"><a href="${ctx }/admin/record/manage" >拜访记录</a></li>
                </ul>
            </li>
            <li class="sub-menu" id="admin-schedule">
                <a class="" href="javascript:;">
                    <i class="icon-dashboard"></i>
                    <span>日程管理</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li id="admin-schedule-manage"><a href="${ctx }/admin/schedule/manage" >我的日程</a></li>
                </ul>
            </li>
            <li class="sub-menu" id="admin-approval">
                <a class="" href="javascript:;">
                    <i class="icon-dashboard"></i>
                    <span>审批管理</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li id="admin-task-return"><a href="${ctx }/admin/task/return" >我的审批</a></li>
                    <li id="admin-task-manage"><a href="${ctx }/admin/task/manage" >我的批示</a></li>
                    <li id="admin-collect-profile"><a href="${ctx }/admin/task/finish" >我的签收</a></li>
                </ul>
            </li>
            <li class="sub-menu" id="admin-system">
                <a class="" href="javascript:;">
                    <i class="icon-food"></i>
                    <span>系统管理</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li id="admin-user-manage"><a href="${ctx }/admin/user/manage" >用户管理</a></li>
                    <li id="admin-dict-manage"><a href="${ctx }/admin/dict/manage">标签管理</a></li>
                    <li id="admin-log-manage"><a href="${ctx }/admin/log/manage">日志管理</a></li>
                </ul>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
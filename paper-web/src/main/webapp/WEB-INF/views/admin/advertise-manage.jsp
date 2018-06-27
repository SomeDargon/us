<%--
  Created by IntelliJ IDEA.
  User: 57
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>订单管理<a class="btn-link btn-sm" href="${ctx }/admin/advertise/form" class="btn-link"><span class="glyphicon glyphicon-edit"></span>新订单</a></h4>
<form class="form-inline well" role="form" id="search-form">

    <div class="form-group">
        <label for="postTitle">订单名称:</label>
        <input class="form-control input-sm" name="postTitle" id="postTitle" size="30" type="text">
    </div>
    <div class="form-group">
        <label for="status">状态:</label>
        <select class="form-control span2" id="status" name="status" >
            <option value="">全部</option>
            <c:forEach items="${fns:getDictList('advertise_status')}" var="dict">
                <option value=" ${dict.value}">${dict.label}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="type">类型:</label>
        <select class="form-control input-sm selectpicker" multiple name="type" id="type" data-width="300px" data-style="btn-sm" data-live-search="true">
            <option value="">全部</option>
            <c:forEach items="${fns:getDictList('advertise_type')}" var="dict">
                <option value="${dict.label}" >${dict.label}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="recommend">是否推荐:</label>
        <select class="span2 form-control " name="recommend" id="recommend">
            <option value="">请选择</option>
            <c:forEach items="${fns:getDictList('advertise_command')}" var="dict">
                <option value="${dict.value}" >${dict.label}</option>
            </c:forEach>
        </select>
    </div>
    <button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
    <a href="${ctx }/admin/advertise/manage" class="btn btn-default btn-sm">刷新</a>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">
    <table id="advertise-table" class="table table-striped table-hover">
        <thead>
        <tr>
            <th style="width:3%;" ><input type="checkbox" name="post[]" value="24"></th>
            <th style="width:3%;">#</th>
            <th style="width:5%;">封面</th>
            <th style="width:30%;"><span class="title_th" name="title"> 标题</span></th>
            <th style="width:15%;">描述</th>
            <th style="width:10%;">链接</th>
            <th style="width:10%;"><span class="title_th" name="postType">类型</span></th>
            <th style="width:5%;">状态</th>
            <th style="width:5%;">来源</th>
            <th style="width:15%;">创建时间</th>
        </tr>
        </thead>
        <tbody>
        {{each result as value i}}
        <tr>
            <td><input type="checkbox" name="post[]" value="24"></td>
            <td>{{ value.id }}</td>
            <td><img class="img-rounded" width="50px" src="{{ value.attachmentUrl }}/45x45" style="margin-right: 5px;" ></td>
            <td class="row-title">
                <a class="btn-link" href="${ctx}/admin/advertise/form?id={{ value.id }}">{{ value.title }}</a> -
                <c:forEach items="${fns:getDictList('advertise_status')}" var="dict">
                    {{if value.postStatus == '${dict.value}'}}${dict.label}{{/if}}
                </c:forEach>
                <div class="row-actions">
                    <a class="btn-link" href="${ctx}/admin/advertise/form?id={{ value.id }}">编辑</a>|
                    <a class="btn-link advertise-del" data='{"id":"{{ value.id }}"}'>删除</a>
                </div>
            </td>
            <td>{{ value.description}}</td>
            <td>{{ value.pushUrl }}</td>
            <td>{{ value.type }}</td>
            <td>
                <c:forEach items="${fns:getDictList('advertise_status')}" var="dict">
                    {{ if value.status == '${dict.value}' }}
                        {{if value.status == 1}}
                        <span class="label label-default">${dict.label}</span>
                        {{else}}
                        <span class="label label-info">${dict.label}</span>
                        {{/if}}
                    {{/if}}
                </c:forEach>
            </td>
            <td>{{ value.source }}</td>
            <td><span class="timeago" title="{{ value.createDate }}" ></span></td>
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
    requirejs(['${ctx}/assets/js/app/admin/advertise-manage-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/advertise-manage-page.js']);
    </c:if>
</script>

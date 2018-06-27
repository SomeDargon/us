    <%--
  Created by IntelliJ IDEA.
  User: 57
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<h2>
    ${empty advertise.id?"添加订单":"编辑订单"}
</h2>
<div class="error class alert-danger" style="display: none;padding:10px 0 8px 10px"></div>
<form id="advertise-form" class="form-horizontal" role="form" style="padding-left: 10px;">
    <div class="row">
    <div class="col-md-8">
        <div class="form-group">
            <label for="title" class=" col-sm-2 control-label">订单标题：</label>
            <input type="hidden" id="id" name="id" value="${advertise.id }"/>
            <div class="col-sm-8">
                <div class="form-group">
                    <input type="text" class="form-control required" id="title" name="title" value="${advertise.title }"
                           placeholder="在此填写订单名称" style="font-weight: 500;font-size: 18px; height: 42px;" title="还没有写订单名称呢~^_^">
                </div>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-2 control-label">订单图片：</label>
            <input type="hidden" id="photo-id" name="attachment" value="${advertise.attachment}"/>
            <div class="col-sm-8">
                <div class="media" style="border-bottom: 0;">
                    <a class="pull-left" href="#">
                        <c:if test="${advertise.attachment!=null}">
                            <img id="avatar-preview" class="media-object img-rounded" src="${ctx}/commons/attachment/download/${advertise.attachment}/100x100" alt="..." style="width: 100px;height: 100px;">
                        </c:if>
                        <c:if test="${advertise.attachment==null}">
                            <img id="avatar-preview" class="media-object img-rounded" alt="..." style="width: 100px;height: 100px;">
                        </c:if>
                    </a>
                    <div class="media-body  visible-sm visible-md visible-lg" style="width: 200px;">
                        <button type="button" class="btn btn-default btn-sm avatar-edit-button">选择图片</button>
                        <p class="help-block">支持 JPG、PNG、GIF 格式，不超过 2M 。</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="pushUrl" class=" col-sm-2 control-label">链接：</label>
            <div class="col-sm-8">
                <input type="text" class="form-control input-sm required" id="pushUrl" name="pushUrl" value="${advertise.pushUrl}">
            </div>
        </div>

        <div class="form-group">
            <label for="type" class=" col-sm-2 control-label">类型：</label>
            <div class="col-sm-8">
                    <select class="form-control input-sm selectpicker"  multiple id="type" name="type" data-live-search="true">
                    <c:forEach items="${fns:getDictList('advertise_type')}" var="dict">
                        <option value="${dict.label}" >${dict.label}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label" for="source" >文章来源：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control input-sm" name="source" id="source">
            </div>
        </div>

        <div class="form-group">
            <label for="status" class=" col-sm-2 control-label">状态：</label>
            <div class="col-sm-4">
                <c:forEach items="${fns:getDictList('advertise_status')}" var="dict">
                    <input type="radio" id="radio${dict.id }" class="required" id="status" name="status" ${advertise.status==dict.value?"checked":""} value="${dict.value }" title="状态"> ${dict.label }
                </c:forEach>
            </div>
        </div>

        <div class="form-group">
            <label for="recommend" class=" col-sm-2 control-label">推荐：</label>
            <div class="col-sm-4">
                <c:forEach items="${fns:getDictList('advertise_command')}" var="dict">
                    <input type="radio" id="radio${dict.id }" class="required" id="recommend" name="recommend" ${advertise.recommend==dict.value?"checked":""} value="${dict.value }" title="推荐"> ${dict.label }
                </c:forEach>
            </div>
        </div>

        <div class="form-group">
            <label for="description" class=" col-sm-2 control-label">描述：</label>
            <div class="col-sm-8">
                <textarea id="description" name="description" class="form-control input-sm required" rows="4" cols="40">${advertise.description}</textarea>
            </div>
        </div>
        <div  style="clear: both">

                <button id="advertise-submit" class="btn btn-primary btn-sm" type="button">保 存</button>

            <button id="advertise-cancel" class="btn btn-sm" type="button" onclick="history.go(-1)">返 回</button>
        </div>
    </div>
    </div>
</form>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/advertise-form-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/advertise-form-page.js']);
    </c:if>
</script>

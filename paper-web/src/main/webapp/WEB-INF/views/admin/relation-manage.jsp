<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h4>人脉管理<a class="btn-link btn-sm" href="${ctx }/admin/relation/form" class="btn-link"><span class="glyphicon glyphicon-plus-sign"></span>添加人脉</a></h4>
<form class="form-inline well" role="form" id="search-form">
    <div class="form-group">
        <label for="name">用户名:</label>
        <input class="form-control input-sm" name="name" id="name" size="30" type="text">
    </div>
    <button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
    <a href="${ctx }/admin/personal/manage" class="btn btn-default btn-sm">刷新</a>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">
    <table id="dict-table" class="table table-bordered table-condensed table-hover">
        <thead>
        <tr>
            <th style="width:15%;">用户名</th>
            <th style="width:20%;">账号</th>
            <th style="width:10%;">性别</th>
            <th style="width:10%;">Email</th>
            <th style="width:10%;">微信</th>
            <th style="width:10%;">电话</th>
            <th style="width:10%;">QQ</th>
            <shiro:hasPermission name="sys:menu:edit"><th>操作</th></shiro:hasPermission>
        </tr>
        </thead>
        <tbody>
        {{each result as value i}}
        <tr>
            <td>{{ value.name }}</td>
            <td>{{ value.uname }}</td>
            <td><c:forEach items="${fns:getDictList('user_gender')}" var="dict">
                {{if value.sex == '${dict.value}'}}${dict.label}{{/if}}
            </c:forEach></td>
            <td>{{ value.email }}</td>
            <td>{{ value.weChat }}</td>
            <td>{{ value.iphone }}</td>
            <td>{{ value.qq }}</td>

            <shiro:hasPermission name="sys:menu:edit">
                <td>
                    <a class="btn-link btn-view" data-toggle="modal" data-target="#releView" data='{"row":{"id":"{{value.id}}","name":"{{value.name}}","uname":"{{value.uname}}","sex":"<c:forEach items='${fns:getDictList(\'user_gender\')}' var='dict'>{{if value.sex == '${dict.value}'}}${dict.label}{{/if}}</c:forEach>","email":"{{value.email}}","weChat":"{{value.weChat}}","iphone":"{{value.iphone}}","qq":"{{value.qq}}","industry":"{{value.industry}}","companyName":"{{value.companyName}}","insertTime":"{{value.insertTime}}" } }' >查看</a>
                    <a class="btn-link" href="${ctx}/admin/relation/form?id={{ value.id }}">修改</a>
                    <button type="button" class="btn-link relation-del" data='{"id":"{{ value.id }}"}'>删除</button>
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

    <div class="modal fade" id="releView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel" style="text-align: center">个人详情</h4>
                </div>
                <div class="modal-body">
                    <ul class="person-ul">
                        <li class="person-li-left">
                            <div class="person-title title-size padding-title"><span class="span-left">姓</span><span class="span-right">名<span class="span-point">：</span></span></div>
                            <div class="person-content"> <input id="username" type="text"  class="form-control btn-size"  aria-describedby="basic-addon1" ></div>
                        </li>
                        <li class="person-li-right">
                            <div class="person-title title-size padding-title"><span class="span-left">账</span><span class="span-right">号<span class="span-point">：</span></span></div>
                            <div class="person-content"> <input id="userEnName" type="text"  class="form-control btn-size"  aria-describedby="basic-addon1" ></div>
                        </li>
                        <li class="person-li-left">
                            <div class="person-title title-size padding-title">联系电话：</div>
                            <div class="person-content"> <input id="iphone" type="text"  class="form-control btn-size"  aria-describedby="basic-addon1" ></div>
                        </li>
                        <li class="person-li-right">
                            <div class="person-title title-size padding-title"><span class="span-left">性</span><span class="span-right">别<span class="span-point">：</span></span></div>
                            <div class="person-content"> <input id="sex" type="text"  class="form-control btn-size"  aria-describedby="basic-addon1" ></div>
                        </li>
                        <li class="person-li">
                            <div class="person-title title-size padding-title">单位名称：</div>
                            <div class="person-content person-line "> <input id="companyName" type="text"  class="form-control btn-size"  aria-describedby="basic-addon1" ></div>
                        </li>
                        <li class="person-li">
                            <div class="person-title title-size padding-title">工作地址：</div>
                            <div class="person-content person-line "> <input id="industry" type="text"   class="form-control btn-size"  aria-describedby="basic-addon1" ></div>
                        </li>
                        <li class="person-li">
                            <div class="person-title title-size padding-title top-content">备注：</div>
                            <div class="person-content person-line "> <input id="remark" type="text"  class="form-control btn-size btn-height"  aria-describedby="basic-addon1" ></div>
                        </li>
                        <div class="clearfix"></div>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>


<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/inter-relation-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/inter-relation-page.js']);
    </c:if>
</script>

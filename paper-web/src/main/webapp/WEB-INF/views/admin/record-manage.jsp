<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h4>拜访记录</h4>

<div id="list-data"></div>

<script id="list-template" type="text/html">
    <table id="dict-table" class="table table-bordered table-condensed table-hover">
        <thead>
        <tr>
            <th style="width:15%;">访问时间</th>
            <th style="width:20%;">用户名</th>
            <th style="width:20%;">访问模块</th>
        </tr>
        </thead>
        <tbody>
        {{each result as value i}}
        <tr>
            <td>{{ value.insertTime }}</td>
            <td>{{ value.user.loginName }}</td>
            <td>{{ value.module }}</td>
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
    requirejs(['${ctx}/assets/js/app/admin/record-manager-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/record-manager-page.js']);
    </c:if>
</script>

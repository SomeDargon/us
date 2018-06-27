<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>待审批</h4>
<div class="error alert alert-danger" style="display:none;">
    <input type="hidden" value="${id}" id="p_id">
</div>
    <div class="col-lg-12 col-log-12-padding">
        <div class="ibox ibox-bottom float-e-margins border-bottom">
            <div class="ibox-title">
                <h5 id="h-title"></h5>
                <div class="ibox-tools">
                    <a class="collapse-link0">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content" style="display: block;">
                <div class="row m-b-lg">
                    <div class="col-lg-12">
                        <table class="table table-bordered">
                            <tr>
                                <td class="table-center">处理人</td>
                                <td id="dealPeople"></td>
                                <td class="table-center">发起时间</td>
                                <td id="insertTime"></td>
                                <td class="table-center">类型</td>
                                <td id="type"></td>
                            </tr>
                            <tr>
                                <td class="table-center">意见</td>
                                <td colspan="5" id="x1"></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/task-process-deal.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/task-process-deal.js']);
    </c:if>
</script>
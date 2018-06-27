<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<section class="hero" id="intro">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-right navicon">
                <a id="nav-toggle" class="nav_slide_button" href="#"><span></span></a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center inner">
                <div class="animatedParent">
                    <h1 class="animated fadeInDown">CRM</h1>
                    <p class="animated fadeInUp">客户关系管理系统</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-md-offset-3 text-center">
                <a href="#contact" class="learn-more-btn btn-scroll">More</a>
            </div>
        </div>
    </div>
</section>


<!-- Navigation -->
<div id="navigation">
    <nav class="navbar navbar-custom" role="navigation">
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <div class="site-logo">
                        <a href="index.html" class="brand">CRM</a>
                    </div>
                </div>
                <div class="col-md-10">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="menu">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><a href="#intro">首页</a></li>
                            <li><a href="#contact">登陆</a></li>
                        </ul>
                    </div>
                    <!-- /.Navbar-collapse -->

                </div>
            </div>
        </div>
        <!-- /.container -->
    </nav>
</div>

<!-- Section: contact -->
<section id="contact" class="home-section nopadd-bot color-dark bg-gray text-center">
    <div class="container marginbot-50">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <div class="animatedParent">
                    <div class="section-heading text-center">
                        <h2 class="h-bold animated bounceInDown">登录CRM</h2>
                        <div class="divider-header"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container" style="width: 670px;">
        <div class="row marginbot-80">
            <div class="col-md-8 col-md-offset-2">
                <div id="sendmessage">Your message has been sent. Thank you!</div>
                <div id="errormessage"></div>
                <form id="login-form" class="form-horizontal"  method="post" role="form" class="contactForm">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <input type="text" class="form-control" value="${username }" name="username" id="username" placeholder="账号  " data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
                                <div class="validation"></div>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="password" id="password" placeholder="密码" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
                                <div class="validation"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-5">
                                    <label>
                                        <a href="${ctx}/forget">忘记密码？</a>
                                        <input type="checkbox" id="rememberMe" name="rememberMe"> 记住我
                                    </label>
                                </div>
                            </div>
                            <button type="button" class="btn btn-skin btn-lg btn-block" id="login-submit">
                                登录
                            </button>
                            <c:choose>
                                <c:when test="${shiroLoginFailure eq 'com.thon.security.CaptchaException'}">
                                    <small><span class="text-danger"><fmt:message key="login.error.captcha" /></span></small>
                                </c:when>
                                <c:when test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}">
                                    <small><span class="text-danger"><fmt:message key="login.error.notExist" /></span></small>
                                </c:when>
                                <c:when
                                        test="${shiroLoginFailure eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
                                    <small><span class="text-danger">登录名或密码错误</span></small>
                                </c:when>
                                <c:when
                                        test="${shiroLoginFailure eq 'org.apache.shiro.authc.LockedAccountException'}">
                                    <small><span class="text-danger"><fmt:message key="login.error.inactive" /><a href="#"><fmt:message key="login.error.activeEmail" /></a>.</span></small>
                                </c:when>
                                <c:when test="${shiroLoginFailure ne null}">
                                    <small><span class="text-danger"><fmt:message key="login.error.other" /></span></small>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <div class="error alert alert-danger col-sm-offset-4 col-sm-5" style="display:none;"></div>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- /Section: contact -->


<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <ul class="footer-menu">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">Press release</a></li>
                </ul>
            </div>
            <div class="col-md-6 text-right copyright">
                &copy;Copyright - 57. All Rights Reserved
                <div class="credits">
                    <a href="">QQ</a> by <a href="https://bootstrapmade.com/">449652684
                </a>
                </div>
            </div>
        </div>
    </div>
</footer>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/login-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/login-page.js']);
    </c:if>
</script>

<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!-- saved from url=(0055)http://ironsummitmedia.github.io/startbootstrap-agency/ -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网页不存在 · 客户关系管理系统</title>
    <link rel="shortcut icon" href="">
    <style type="text/css" media="screen">
        body {
            background-color: #f1f1f1;
            margin: 0;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        .container {
            margin: 50px auto 40px auto;
            width: 600px;
            text-align: center;
        }

        a {
            color: #4183c4;
            text-decoration: none;
        }

        a strong {
            color: #c0c0c0;
        }

        a:hover {
            text-decoration: none;
        }

        h1 {
            width: 800px;
            position: relative;
            left: -100px;
            letter-spacing: -1px;
            line-height: 60px;
            font-size: 60px;
            font-weight: 100;
            margin: 0px 0 50px 0;
            text-shadow: 0 1px 0 #fff;
        }

        p {
            color: rgba(0, 0, 0, 0.5);
            margin: 20px 0;
            line-height: 1.6;
        }

        ul {
            list-style: none;
            margin: 25px 0;
            padding: 0;
        }

        li {
            display: table-cell;
            font-weight: bold;
            width: 1%;
        }

        .logo {
            display: inline-block;
            margin-top: 35px;
        }

        @media only screen and (-webkit-min-device-pixel-ratio: 2), only screen and (   min--moz-device-pixel-ratio: 2), only screen and (     -o-min-device-pixel-ratio: 2/1), only screen and (        min-device-pixel-ratio: 2), only screen and (                min-resolution: 192dpi), only screen and (                min-resolution: 2dppx) {
            .logo-img-1x {
                display: none;
            }
        }

        #suggestions {
            margin-top: 35px;
            color: #ccc;
        }

        #suggestions a {
            color: #666666;
            font-weight: 200;
            font-size: 14px;
            margin: 0 10px;
        }

    </style>

</head>
<body gtools_scp_screen_capture_injected="true">

<div class="container">

    <h1><strong>404</strong></h1>

    <p><strong>很抱歉，您访问的页面已失效或者不存在</strong></p>


    <p>
        <span id="jumpTo" style="color:#FF0000;font-weight: bold;font-size:14px;padding:0px 5px;">5</span>秒后自动跳转回
        <a href="javascript:history.go(-1)"><strong>上一个页面</strong></a>
    </p>

    <div id="suggestions">
        <a href="http://www.scujcc.com.cn/">四川大学锦城学院</a> —
        <a href="http://www.scujcc.com.cn/">@计科系毕业论文管理系统</a>
    </div>

    <a href="${ctx}" class="logo logo-img-1x">
        <img width="50" height="50" title="" alt=""
             src="./assets/img/lianxi.png">
    </a>

</div>
<script type="application/javascript">
    function countDown(secs, surl) {
        var jumpTo = document.getElementById('jumpTo');
        jumpTo.innerHTML = secs;
        if (--secs > 0) {
            setTimeout("countDown(" + secs + ",'" + surl + "')", 1000);
        } else {
            if (!history.go(-1) ==undefined) {
                location.href = history.go(-1);
            }else {
                var pathName = document.location.pathname;
                var index = pathName.substr(1).indexOf("/");
                var result = pathName.substr(0,index+1);
                location.href = result;
            }
        }
    }
    countDown(5, "")
</script>

</body>
</html>
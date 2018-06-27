/**
 * Created by 57 on 2017/2/18.
 */
define(function(require, exports, module){

    var $ = require('jquery');
    require('bootstrap');
    require('bootstrap.select');
    require('jquery.layer');
    require('jquery.icheck');

    var List = require('../../util/list.js');


    exports.loadList = function(param){
        var list = new List();
        list.options = {
            template : "list-template",
            container : "list-data",
            url : $CONFIG.base_url + "/api/topic/choice/list",
            param : param
        };
        list.load();
    };
    exports.loadList();

    $(".btn-search-submit").click(function(){
        var $form = $("#search-form");

        var postData = $form.serialize();
        exports.loadList(postData);
    });

    $("#list-data").on("click",".choice-del",function(){
        var data = $.parseJSON($(this).attr("data"));
        var $this = $(this);
        layer.confirm('要删除该记录？', {
            btn: ['确认','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var posting = $.post($CONFIG.base_url + "/api/choice/del", data);
            posting.done(function(data){
                layer.msg('删除成功', {icon:9});
                $this.parents("tr").remove();
            }).fail(function() {
                layer.msg('无权限或出现错误，请联系管理员', {icon:9});
            })
        }, function(){
        });
    });

    $("#list-data").on("click",".choice-pass",function(){
        var data = $.parseJSON($(this).attr("data"));
        var $this = $(this);
        layer.confirm('是否通该选题？', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var posting = $.post($CONFIG.base_url + "/api/topic/choice/educator/pass", data);
            posting.done(function(data){
                    if(data == "true") {
                        layer.msg('成功', {
                            icon:1,
                            time:1000
                        }, function () {
                            window.location.href = $CONFIG.base_url + "/admin/choice/manage";
                        });
                    }
                    if(data == "had_pass") {
                        layer.msg('您已经通过了！', {icon:9});
                    }
                    if(data == "wait_teacher") {
                        layer.msg('请先等待教师审核通过！', {icon:9});
                    }
                    if(data == "teacher_nopass") {
                        layer.msg('老师不让过啊！', {icon:9});
                    }
                    if(data == "invalid") {
                        layer.msg('该题目已经失效！', {icon:9});
                    }
            });
        }, function(){
        });
    });


    $("#list-data").on("click",".choice-nopass",function(){
        var data = $.parseJSON($(this).attr("data"));
        var $this = $(this);
        layer.confirm('是否不通过该选题？', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var posting = $.post($CONFIG.base_url + "/api/topic/choice/educator/nopass", data);
            posting.done(function(data){
                if(data == "true") {
                    layer.msg('成功', {
                        icon:1,
                        time:1000
                    }, function () {
                        window.location.href = $CONFIG.base_url + "/admin/choice/manage";
                    });
                }
                if(data == "had_nopass") {
                    layer.msg('请不要做无意义的操作！', {icon:9});
                }
                if(data == "wait_teacher") {
                    layer.msg('请先等待教师做处理！', {icon:9});
                }
                if(data == "teacher_nopass") {
                    layer.msg('老师已经处理过了！', {icon:9});
                }
                if(data == "invalid") {
                    layer.msg('该题目已经失效！', {icon:9});
                }
            });
        }, function(){
        });
    });


});

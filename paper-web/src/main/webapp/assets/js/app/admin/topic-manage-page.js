/**
 * Created by 57 on 2017/2/9.
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
            url : $CONFIG.base_url + "/api/topic/list",
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

    $("#list-data").on("click",".topic-del",function(){
        var data = $.parseJSON($(this).attr("data"));
        var $this = $(this);
        layer.confirm('要删除该记录？', {
            btn: ['确认','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var posting = $.post($CONFIG.base_url + "/api/topic/del", data);
            posting.done(function(data){
                layer.msg('删除成功', {icon:9});
                $this.parents("tr").remove();
            }).fail(function() {
                layer.msg('无权限或出现错误，请联系管理员', {icon:9});
            })
        }, function(){
        });
    });

    $("#list-data").on("click",".topic-pass",function(){
        var data = $.parseJSON($(this).attr("data"));
        layer.confirm('是否通过审核？', {
            btn: ['通过','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var posting = $.post($CONFIG.base_url + "/api/topic/educator/pass", data);
            posting.done(function(data) {
                if (data == true) {
                    layer.msg('成功', {icon: 9});
                    window.location.href = $CONFIG.base_url + "/admin/topic/manage";
                } else {
                    layer.msg('教师还未通过审核', {
                        icon: 9,
                        time: 2000
                    });
                }
            });
        }, function(){
        });
    });



});

/**
 * Created by 57 on 2017/1/27.
 */

define(function(require, exports, module){
    var $ = require('jquery');
    require('bootstrap');
    require('bootstrap.select');
    require('jquery.layer');
    require('jquery.timeago')

    var List = require('../../util/list.js');

    $('.selectpicker').selectpicker({
        'selectedText': 'cat',

    });
    exports.loadList = function(param){
        var list = new List();
        list.options = {
            template : "list-template",
            container : "list-data",
            url : $CONFIG.base_url + "/api/post/advertise/list",
            param : param
        };
        list.load(function(){
            $(".timeago").timeago();
            $(".title_th").each(function(){
                $(this).on("click", function(){
                    exports.loadList({"orderName":$(this).attr("name")});
                })
            })
        });
    };
    exports.loadList();
    $(".btn-search-submit").click(function(){
        var $form = $("#search-form");

        var postData = $form.serialize();
        exports.loadList(postData);
    });



    $("#list-data").on("click",".advertise-del",function(){
        var data = $.parseJSON($(this).attr("data"));
        var $this = $(this);
        layer.confirm('要删除该记录？', {
            btn: ['确认','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var posting = $.post($CONFIG.base_url + "/api/post/advertise/del", data);
            posting.done(function(data){
                layer.msg('删除成功', {icon:9});
                $this.parents("tr").remove();
            }).fail(function() {
                layer.msg('无权限或出现错误，请联系管理员', {icon:9});
            })
        }, function(){
        });

    });



});

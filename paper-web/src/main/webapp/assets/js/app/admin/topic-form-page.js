/**
 * Created by 57on 2017/2/9.
 */
define(function(require, exports, module){

    var $ = require('jquery');
    var List = require('../../util/list.js');
    require('jquery.validate');
    require('jquery.icheck');
    require('bootstrap');
    require('bootstrap.select');
    require('jquery.layer');

    // 初始化
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });
    exports.getCityTypeSelect = function(parent, type, callback) {
        $.get($CONFIG.base_url + "/api/office/find/list", {
            type : parent
        }, function(data) {

            var i, len = data.length, html = '',tasktype='';
            for (i = 0; i < len; i++) {
                if($("#region-" + type + "-id").val()!=""){
                    if($("#region-" + type + "-id").val()==data[i].id){
                        html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                        tasktype = data[i].id;
                        $("#region-" + type + "-id").val(data[i].name);
                    }else {
                        html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }else if (i==0){
                    html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                    tasktype = data[i].id;
                    $("#region-" + type + "-id").val(data[i].name);
                }
            }
            $("#region-" + type + "-select").empty().append(html);
            switch (type) {
                case "province":
                    exports.getUser(tasktype, "city");
                    break;
                default:
                    break;
            }
            if (typeof (callback) == "function") {
                callback(data);
            }

        });
    };
    exports.getCityTypeSelect(1,"province");

    exports.getUser = function(parent, type, callback) {
        $.get($CONFIG.base_url + "/api/user/find/list", {
            "office_id" : parent,
            "type":2
        }, function(data) {
            var i, len = data.length, html = '',tasktype='';
            for (i = 0; i < len; i++) {
                if($("#region-" + type + "-id").val()!=""){
                    if($("#region-" + type + "-id").val()==data[i].id){
                        html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                        tasktype = data[i].id;
                        $("#region-" + type + "-id").val(data[i].name);
                    }else {
                        html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }else if (i==0){
                    html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                    tasktype = data[i].id;
                    $("#region-" + type + "-id").val(data[i].name);
                }

            }
            $("#region-" + type + "-select").empty().append(html);
        });
    }
    $("#region-province-select").change(function() {
        exports.getUser($(this).val(), "city");
        $("#region-province-id").val($(this).val());
        $("#region-city-id").val("");
    });

    $("#region-city-select").change(function() {
        $("#region-city-id").val($(this).val());
    });

    $("#topic-submit").click(function(){
        var $form = $("#topic-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();


        var posting = $.post($CONFIG.base_url + "/api/topic/save", postData);
        posting.done(function(data) {
            layer.msg('保存成功', {
                time: 4000 //4s后自动关闭
            });
            window.location.href = $CONFIG.base_url + "/admin/topic/manage";
        });
    });

    exports.getTeacher = function(parent, type, callback) {
        $.get($CONFIG.base_url + "/api/office/find/list", {
            type : parent
        }, function(data) {

            var i, len = data.length, html = '',tasktype='';
            for (i = 0; i < len; i++) {
                if($("#re-" + type + "-id").val()!=""){
                    if($("#re-" + type + "-id").val()==data[i].id){
                        html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                        tasktype = data[i].id;
                        $("#re-" + type + "-id").val(data[i].name);
                    }else {
                        html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }else if (i==0){
                    html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                    tasktype = data[i].id;
                    $("#re-" + type + "-id").val(data[i].name);
                }
            }
            $("#re-" + type + "-select").empty().append(html);
            switch (type) {
                case "province":
                    exports.getTeacherUser(tasktype, "city");
                    break;
                default:
                    break;
            }
            if (typeof (callback) == "function") {
                callback(data);
            }

        });
    };
    exports.getTeacher(1,"province");

    exports.getTeacherUser = function(parent, type, callback) {
        $.get($CONFIG.base_url + "/api/user/find/list", {
            "office_id" : parent,
            "type":2
        }, function(data) {
            var i, len = data.length, html = '',tasktype='';
            for (i = 0; i < len; i++) {
                if($("#re-" + type + "-id").val()!=""){
                    if($("#re-" + type + "-id").val()==data[i].id){
                        html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                        tasktype = data[i].id;
                        $("#re-" + type + "-id").val(data[i].name);
                    }else {
                        html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }else if (i==0){
                    html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                    tasktype = data[i].id;
                    $("#re-" + type + "-id").val(data[i].name);
                }

            }
            $("#re-" + type + "-select").empty().append(html);
        });
    }
    $("#re-province-select").change(function() {
        exports.getTeacherUser($(this).val(), "city");
        $("#re-province-id").val($(this).val());
        $("#re-city-id").val("");
    });

    $("#re-city-select").change(function() {
        $("#re-city-id").val($(this).val());
    });
});

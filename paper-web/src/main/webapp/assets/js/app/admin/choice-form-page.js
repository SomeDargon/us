/**
 * Created by 57 on 2017/2/18.
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


    $("#choice-submit").click(function(){
        var $form = $("#choice-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();


        var posting = $.post($CONFIG.base_url + "/api/topic/choice/save", postData);
        posting.done(function(data) {
            layer.msg('保存成功', {
                time: 4000 //4s后自动关闭
            });
            window.location.href = $CONFIG.base_url + "/admin/choice/manage";
        });
    });

    ////不可修改
    //var choice_id = $("#id").val();
    //if(choice_id != 0){
    //    $("#name").attr("disabled",true);
    //    $(".typeDisable").attr("disabled",true);
    //    $(".sourceDisable").attr("disabled",true);
    //    $("#restriction").attr("disabled",true);
    //    $("#description").attr("disabled",true);
    //    $("#creator").attr("disabled",true);
    //    $("#pass").attr("disabled",true);
    //}



});

/**
 * Created by 57 on 2016/12/28.
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

    $("#term-submit").click(function(){
        var $form = $("#term-form");
        if(!$form.valid()){
            return;
        }

        var postData = $form.serialize();


        var posting = $.post($CONFIG.base_url + "/api/post/term/save", postData);
        posting.done(function(data) {
            layer.msg('保存成功', {
                time: 4000, //4s后自动关闭
            });
            window.location.href = $CONFIG.base_url + "/admin/term/manage";
        });
    });

});

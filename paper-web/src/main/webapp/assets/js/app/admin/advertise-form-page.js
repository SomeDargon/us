/**
 * Created by 57on 2017/1/27.
 */
define(function(require, exports, module){

    var $ = require('jquery');
    var List = require('../../util/list.js');
    var ImgCorp = require('../../util/img-corp.js');
    require('jquery.validate');
    require('jquery.layer');
    require('jquery.icheck');
    require('bootstrap');
    require('bootstrap.select');


    // 初始化
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    $('.selectpicker').selectpicker({
        'selectedText': 'cat',
        style: 'btn-success,'
    });



    $("#advertise-submit").click(function(){
        var $form = $("#advertise-form");
        if(!$form.valid()){
            return;
        }
        var postData = $form.serialize();

        var posting = $.post($CONFIG.base_url + "/api/post/advertise/save", postData);
        posting.done(function(data) {
            layer.msg('保存成功', {
                time: 4000 //4s后自动关闭
            });
            window.location.href = $CONFIG.base_url + "/admin/advertise/manage";
        });
    });

    /***********************添加订单图片***************************/
    $('.avatar-edit-button').click(function () {
        var advertiseId = $("#id").val();
        var imgCorp = new ImgCorp();
        imgCorp.init({resize:false, aspectRatio: 1}, function (data) {
            var imgId = data.id;
            if(advertiseId != ""){
                var posting = $.post($CONFIG.base_url + "/api/post/advertise/profile/avatar/update", {id: advertiseId, attachment: data.id});
                posting.done(function (data) {
                    $('#avatar-preview').attr("src", $CONFIG.base_url + '/commons/attachment/download/' + imgId + "?t="+new Date());
                    $("#photo-id").val(imgId);
                });
            }else {
                $('#avatar-preview').attr("src", $CONFIG.base_url + '/commons/attachment/download/' + imgId + "?t=" + new Date());
                $("#photo-id").val(imgId);
            }
        });
    });


    var type = $("#type");
    //回显
    $("#typeSelect option").each(function(){
        if(type.val() == $(this).val()){
            $(this).attr("selected",true);
        }
    });
    //保存
    $("#typeSelect").on('change',function(){
        type.val($(this).val());
    });


});

define(function(require, exports, module){

    var $ = require('jquery');
    var List = require('../../util/list.js');
    require('jquery.validate');
    require('jquery.icheck');
    require('bootstrap');
    require('bootstrap.select');
    require('jquery.layer');
    require('dateSelect');

    // 初始化
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    $.validator.addMethod("isPhone", function(value,element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return this.optional(element) || (tel.test(value) || mobile.test(value));

    }, "请正确填写您的联系电话");
	
	$("#relation-form").validate({
		errorContainer: $("div.error"),
		errorLabelContainer: $("div.error"),
		wrapper: 'li',
        rules : {
            email : {
                email: true
            }
        },
        messages : {
            email : {
                email: '请输入正确的邮箱格式'
            }
        }
	});
	
	$("#relation-submit").click(function(){
		var $form = $("#relation-form");
		if(!$form.valid()){
			return;
		}
		
		var postData = $form.serialize();
		var posting = $.post($CONFIG.base_url + "/inter/relation/save", postData);
		posting.done(function(data) {
            layer.msg('保存成功', {
                time: 4000 //4s后自动关闭
            });
            window.location.href = $CONFIG.base_url + "/admin/personal/manage";
		});
	});
	
});

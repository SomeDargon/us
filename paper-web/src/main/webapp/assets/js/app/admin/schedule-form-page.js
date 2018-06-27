define(function(require, exports, module){

    var $ = require('jquery');
    var List = require('../../util/list.js');
    require('jquery.validate');
    require('jquery.icheck');
    require('bootstrap');
    require('bootstrap.select');
    require('jquery.layer');
    require('dateSelect');
    require('bootstrap.datetimepicker');
    // 初始化 iCheck复选框
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });
    $('.form_datetime').datetimepicker({    //date的插件调用
        language:  'zh',
        minView:'month'
    });

	
	$("#schedule-submit").click(function(){
		var $form = $("#schedule-form");
		if(!$form.valid()){  //判断form框中有值吗
			return;
		}
		
		var postData = $form.serialize();//获取form框中的至
		var posting = $.post($CONFIG.base_url + "/schedule/save", postData);
		posting.done(function(data) {
            layer.msg('保存成功', {
                time: 4000 //4s后自动关闭
            });
            window.location.href = $CONFIG.base_url + "/admin/schedule/manage";
		});
	});
	
});
